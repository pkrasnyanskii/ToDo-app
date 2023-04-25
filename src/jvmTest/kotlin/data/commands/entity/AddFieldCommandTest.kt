package data.commands.entity

import org.junit.Assert.*
import org.junit.Test

class AddFieldCommandTest {

    private val addFieldCommand = AddFieldCommand(
        objectId = testObjectId,
        fieldId =  testFieldId,
        fieldName = "description",
        fieldType = "string"
    )

    @Test
    fun `WHEN AddFieldCommand executes EXPECT ModelObject's fields list size increased by one`() {
        val initialSize = testModelObject.fields.size

        addFieldCommand.execute(testObjectsStorage)

        assertEquals(initialSize + 1, testObjectsStorage.getObjectById(testObjectId).fields.size)
        assertEquals(initialSize + 1, testModelObject.fields.size)
    }
}