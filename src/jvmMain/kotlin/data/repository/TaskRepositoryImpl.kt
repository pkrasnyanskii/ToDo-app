package data.repository

import data.commands.entity.AddFieldCommand
import data.commands.entity.Command
import data.commands.entity.CreateObjectCommand
import data.commands.entity.SetFieldValueCommand
import data.converter.TaskConverter
import data.model.Model
import data.serialization.Serializer
import domain.entity.Task
import domain.entity.TaskStatus
import domain.repository.TaskRepository
import java.util.UUID

class TaskRepositoryImpl(
    private val model: Model,
    private val converter: TaskConverter,
    private val serializer: Serializer
) : TaskRepository {

    override fun getTasks(): List<Task> =
        model.objectsStorage.objects
            .map { obj -> converter.convert(obj) }
            .filter { task -> task.status != TaskStatus.DELETED }

    override fun addTask(task: Task) {
        val taskObjectId = task.id
        val taskTextFieldId = UUID.randomUUID()
        val taskStatusFieldId = UUID.randomUUID()

        val createTaskCommands: List<Command> = listOf(
            CreateObjectCommand(taskObjectId, "Task"),
            AddFieldCommand(taskObjectId, taskTextFieldId, "taskText", "String"),
            SetFieldValueCommand(taskTextFieldId, task.text),
            AddFieldCommand(taskObjectId, taskStatusFieldId, "taskStatus", "String"),
            SetFieldValueCommand(taskStatusFieldId, task.status)
        )
        createTaskCommands.forEach { command -> handleCommand(command) }
    }

    override fun changeTaskStatus(id: UUID, status: TaskStatus) {
        val taskStatusFieldId = model.objectsStorage
            .getObjectById(id)
            .getFieldByName("taskStatus")
            .id
        val setFieldValueCommand = SetFieldValueCommand(taskStatusFieldId, status)
        handleCommand(setFieldValueCommand)
    }

    override fun deleteTask(id: UUID) {
        val taskStatusFieldId = model.objectsStorage
            .getObjectById(id)
            .getFieldByName("taskStatus")
            .id
        val setFieldValueCommand = SetFieldValueCommand(taskStatusFieldId, TaskStatus.DELETED)
        handleCommand(setFieldValueCommand)
    }

    override fun editTaskText(id: UUID, text: String) {
        val taskTextFieldId = model.objectsStorage
            .getObjectById(id)
            .getFieldByName("taskText")
            .id
        val setFieldValueCommand = SetFieldValueCommand(taskTextFieldId, text)
        handleCommand(setFieldValueCommand)
    }

    private fun handleCommand(command: Command) {
        model.commandsStorage.addCommand(command)
        model.commandExecutor.execute(command, model.objectsStorage)
        serializer.serialize()
    }
}