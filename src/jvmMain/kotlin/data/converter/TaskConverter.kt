package data.converter

import data.model.ModelObject
import domain.entity.Task
import domain.entity.TaskStatus

class TaskConverter {

    fun convert(from: ModelObject): Task =
        Task(
            id = from.id,
            title = from.getFieldByName("title").value.toString(),
            description = from.getFieldByName("description").value.toString(),
            status = TaskStatus.valueOf(from.getFieldByName("status").value.toString())
        )
}