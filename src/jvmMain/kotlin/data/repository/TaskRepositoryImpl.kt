package data.repository

import data.converter.TaskConverter
import data.model.Model
import domain.entity.Task
import domain.repository.TaskRepository

class TaskRepositoryImpl(
    private val model: Model,
    private val converter: TaskConverter
) : TaskRepository {

    override fun getTasks(): List<Task> =
        model.objects
            .map { obj -> converter.convert(obj) }

    override fun addTask(task: Task) {
        TODO("Not yet implemented")
    }
}