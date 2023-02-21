package domain.repository

import domain.entity.Task

interface TaskRepository {

    fun getTasks(): List<Task>

    fun getActiveTasks(): List<Task>

    fun getCompletedTasks(): List<Task>

    fun addTask(task: Task)
}