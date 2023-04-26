package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf

@Composable
fun EditTaskScreen(
    initialValue: String,
    onBackClicked: () -> Unit
) {
    var value by remember { mutableStateOf(initialValue) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row {
            Button(onClick = onBackClicked) {
                Text("Back")
            }
            Text(text = "Edit task")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            InputField(value) { value = it }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = {  }) {
                Text("Done")
            }
        }
    }
}

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(value = value, onValueChange = onValueChange)
}