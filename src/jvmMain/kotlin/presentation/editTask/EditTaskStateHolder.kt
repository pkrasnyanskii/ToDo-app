package presentation.editTask

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import domain.usecase.tasks.EditTaskUseCase
import org.koin.java.KoinJavaComponent.inject
import java.util.UUID

class EditTaskStateHolder(
    val id: UUID,
    initialValue: String
) {
    val editTaskUseCase: EditTaskUseCase by inject(EditTaskUseCase::class.java)

    private val _state = mutableStateOf(initialValue)
    val state: State<String> = _state

    fun onTextChanged(text: String) {
        _state.value = text
    }

    fun onDoneButtonPressed(taskId: UUID, onEditFinished: () -> Unit) {
        editTaskUseCase(id = taskId, text = state.value)
        onEditFinished()
    }
}