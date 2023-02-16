package data.model

import java.util.*

class ModelObject(
    val id: UUID,
    val type: String
) {

    val fields = mutableListOf<Field>()

    fun addField(field: Field) =
        fields.add(field)

    fun getFieldById(fieldId: UUID): Field =
        fields.stream()
            .filter { f -> f.id == fieldId }
            .findFirst()
            .orElseThrow()

    fun getFieldByName(fieldName: String): Field =
        fields.stream()
            .filter { f -> f.name == fieldName }
            .findFirst()
            .orElseThrow()
}