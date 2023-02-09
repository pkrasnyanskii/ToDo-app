package domain.repository

import domain.entity.Task

interface TaskRepository {

    fun getTasks(): List<Task>

    fun addTask(task: Task)
}