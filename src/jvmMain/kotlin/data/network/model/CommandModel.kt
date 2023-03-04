package data.network.model

import data.commands.entity.CommandType

data class CommandModel(
    val id: Int,
    val commandType: CommandType,
    val objectId: String,
    val objectType: String,
    val fieldId: String,
    val fieldName: String,
    val fieldType: String,
    val fieldValue: String
)