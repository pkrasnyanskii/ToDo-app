package ui.screens.edit_task_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import domain.entity.Task
import presentation.editTask.EditTaskStateHolder

@Composable
fun EditTaskScreen(
    task: Task,
    onBackClicked: () -> Unit
) {
    val stateHolder = remember { EditTaskStateHolder(
        id = task.id,
        initialValue = task.text
    ) }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Edit task",
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(16.dp))

        InputField(
            modifier = Modifier.fillMaxWidth()
                .weight(9F),
            value = stateHolder.state.value,
            onValueChange = stateHolder::onTextChanged
        )

        BottomBar(
            onBackClicked = onBackClicked,
            onDoneClicked = { stateHolder.onDoneButtonPressed(task.id, onBackClicked) },
            modifier = Modifier.fillMaxWidth()
                .weight(1F)
        )
    }
}

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.clip(shape = RoundedCornerShape(30.dp))
    )
}

@Composable
fun BottomBar(
    onBackClicked: () -> Unit,
    onDoneClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onBackClicked,
            shape = CircleShape
        ) {
            Text("Back")
        }

        Button(
            onClick = onDoneClicked,
            shape = CircleShape
        ) {
            Text("Done")
        }
    }
}