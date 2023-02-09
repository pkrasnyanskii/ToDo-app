package domain.entity

import java.util.UUID

data class Task(
    val id: UUID,
    var title: String,
    var description: String,
    var status: TaskStatus
)
