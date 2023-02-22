package data.repository

import data.converter.TaskConverter
import data.model.Model
import domain.entity.Task
import domain.entity.TaskStatus
import domain.repository.TaskRepository

class TaskRepositoryImpl(
    private val model: Model,
    private val converter: TaskConverter
) : TaskRepository {

    override fun getTasks(): List<Task> =
        model.storage.objects
            .map { obj -> converter.convert(obj) }

    override fun getActiveTasks(): List<Task> =
        model.storage.objects
            .map { obj -> converter.convert(obj) }
            .filter { task -> task.status == TaskStatus.ACTIVE }

    override fun getCompletedTasks(): List<Task> =
        model.storage.objects
            .map { obj -> converter.convert(obj) }
            .filter { task -> task.status == TaskStatus.COMPLETED }

    override fun addTask(task: Task) {
        TODO("Not yet implemented")
    }
}