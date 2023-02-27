package data.repository

import data.commands.entity.AddFieldCommand
import data.commands.entity.Command
import data.commands.entity.CreateObjectCommand
import data.commands.entity.SetFieldValueCommand
import data.converter.TaskConverter
import data.model.Model
import domain.entity.Task
import domain.entity.TaskStatus
import domain.repository.TaskRepository
import java.util.UUID

class TaskRepositoryImpl(
    private val model: Model,
    private val converter: TaskConverter
) : TaskRepository {

    override fun getTasks(): List<Task> =
        model.objectsStorage.objects
            .map { obj -> converter.convert(obj) }

    override fun getActiveTasks(): List<Task> =
        model.objectsStorage.objects
            .map { obj -> converter.convert(obj) }
            .filter { task -> task.status == TaskStatus.ACTIVE }

    override fun getCompletedTasks(): List<Task> =
        model.objectsStorage.objects
            .map { obj -> converter.convert(obj) }
            .filter { task -> task.status == TaskStatus.COMPLETED }

    override fun addTask(task: Task) {
        val taskObjectId = UUID.randomUUID()
        val taskNameFieldId = UUID.randomUUID()
        val taskDescriptionFieldId = UUID.randomUUID()
        val taskStatusFieldId = UUID.randomUUID()

        val createTaskCommands: List<Command> = listOf(
            CreateObjectCommand(taskObjectId, "Task"),
            AddFieldCommand(taskObjectId, taskNameFieldId, "taskName", "String"),
            SetFieldValueCommand(taskNameFieldId, task.title),
            AddFieldCommand(taskObjectId, taskDescriptionFieldId, "taskDesc", "String"),
            SetFieldValueCommand(taskDescriptionFieldId, task.description),
            AddFieldCommand(taskObjectId, taskStatusFieldId, "taskStatus", "String"),
            SetFieldValueCommand(taskStatusFieldId, task.status)
        )

        model.commandsStorage.commands.addAll(createTaskCommands)
    }
}