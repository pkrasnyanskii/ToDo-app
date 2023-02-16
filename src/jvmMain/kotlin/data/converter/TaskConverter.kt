package data.converter

import data.model.ModelObject
import domain.entity.Task
import domain.entity.TaskStatus

class TaskConverter {

    fun convert(from: ModelObject): Task =
        Task(
            id = from.id,
            title = from.getFieldByName("taskName").value.toString(),
            description = from.getFieldByName("taskDesc").value.toString(),
            status = TaskStatus.valueOf(from.getFieldByName("taskStatus").value.toString())
        )
}