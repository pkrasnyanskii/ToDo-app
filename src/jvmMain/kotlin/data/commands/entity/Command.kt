package data.commands.entity

import data.model.ObjectsStorage

abstract class Command(
    private val commandType: CommandType
) {

    abstract val id: Int

    abstract fun execute(storage: ObjectsStorage)

    companion object {
        @Suppress("JVM_STATIC_ON_CONST_OR_JVM_FIELD")
        @JvmStatic
        protected const val DEFAULT_ID = -1
    }
}