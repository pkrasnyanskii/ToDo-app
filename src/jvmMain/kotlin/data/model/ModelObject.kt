package data.model

import java.util.UUID

class ModelObject(
    val id: UUID,
    val type: String
) {

    val fields = mutableListOf<Field>()

    fun addField(field: Field) =
        fields.add(field)

    fun getFieldById(fieldId: UUID) =
        fields.stream()
            .filter { f -> f.id == fieldId }
            .findFirst()
            .orElseThrow()

    fun getFieldByName(fieldName: String) =
        fields.stream()
            .filter { f -> f.name == fieldName }
            .findFirst()
            .orElseThrow()
}