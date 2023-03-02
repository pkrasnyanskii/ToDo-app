package domain.repository

import domain.entity.Task
import domain.entity.TaskStatus
import java.util.UUID

interface TaskRepository {

    fun getTasks(): List<Task>

    fun getActiveTasks(): List<Task>

    fun getCompletedTasks(): List<Task>

    fun addTask(task: Task)

    fun changeTaskStatus(id: UUID, status: TaskStatus)
}