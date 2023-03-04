package domain.entity

import java.util.UUID

data class Task(
    val id: UUID,
    var text: String,
    var status: TaskStatus
)
