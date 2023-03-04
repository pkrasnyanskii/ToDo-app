package data.commands.entity

enum class CommandType {
    CREATE_OBJECT_COMMAND, ADD_FIELD_COMMAND, SET_FIELD_VALUE;

    override fun toString(): String =
        when (this) {
            CREATE_OBJECT_COMMAND -> "CreateObjectCommand"
            ADD_FIELD_COMMAND -> "AddFieldCommand"
            SET_FIELD_VALUE -> "SetFieldValueCommand"
        }
}