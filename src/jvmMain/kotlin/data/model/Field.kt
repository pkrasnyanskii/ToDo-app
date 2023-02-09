package data.model

import java.util.UUID

class Field(
    val id: UUID,
    val name: String
) {

    lateinit var value: Any
    override fun toString(): String {
        return "$name = $value"
    }
}