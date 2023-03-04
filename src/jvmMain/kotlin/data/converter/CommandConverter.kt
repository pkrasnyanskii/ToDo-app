package data.converter

import data.commands.entity.*
import data.network.model.CommandModel
import java.util.*

class CommandConverter {

    fun convert(from: CommandModel): Command =
        when (from.commandType) {
            CommandType.CREATE_OBJECT_COMMAND ->
                CreateObjectCommand(
                    objectId = UUID.fromString(from.objectId),
                    objectType = from.objectType,
                    id = from.id
                )

            CommandType.ADD_FIELD_COMMAND ->
                AddFieldCommand(
                    objectId = UUID.fromString(from.objectId),
                    fieldId = UUID.fromString(from.fieldId),
                    fieldName = from.fieldName,
                    fieldType = from.fieldType,
                    id = from.id
                )

            CommandType.SET_FIELD_VALUE ->
                SetFieldValueCommand(
                    fieldId = UUID.fromString(from.fieldId),
                    fieldValue = from.fieldValue,
                    id = from.id
                )
        }
}