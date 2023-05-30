package ui.screens.edit_task_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.entity.Task
import presentation.editTask.EditTaskStateHolder
import ui.screens.tasks_screen.ui_elements.WhiteRoundButton

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
        modifier = Modifier.background(Color(255, 235, 230))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Edit task",
            fontSize = 36.sp,
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
        modifier = modifier.clip(shape = RoundedCornerShape(30.dp)),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedIndicatorColor =  Color.Transparent, //hide the indicator
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.Transparent,
            cursorColor = Color.DarkGray
        )
    )
}

@Composable
fun BottomBar(
    onBackClicked: () -> Unit,
    onDoneClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        WhiteRoundButton(
            onClick = onBackClicked,
        ) {
            Text("Back")
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Undo,
                contentDescription = null
            )
        }

        WhiteRoundButton(
            onClick = onDoneClicked,
        ) {
            Text("Done")
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = null
            )
        }
    }
}