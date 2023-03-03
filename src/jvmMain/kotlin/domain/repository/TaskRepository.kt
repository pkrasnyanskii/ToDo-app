package domain.repository

import domain.entity.Task
import domain.entity.TaskStatus
import java.util.UUID

interface TaskRepository {

    fun getTasks(): List<Task>

    fun addTask(task: Task)

    fun changeTaskStatus(id: UUID, status: TaskStatus)

    fun deleteTask(id: UUID)
}