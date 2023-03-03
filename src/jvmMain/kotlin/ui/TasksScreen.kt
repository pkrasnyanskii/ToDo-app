package ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.entity.Task
import domain.entity.TaskStatus
import presentation.TasksStateHolder
import presentation.TasksUiState
import java.util.UUID

@Composable
fun TasksScreen() {
    val stateHolder = remember { TasksStateHolder() }

    stateHolder.loadData()

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Task List") }) },
        bottomBar = { Input(
            text = (stateHolder.state.value as TasksUiState.Content).inputText,
            onTextChanged = stateHolder::onInputTextChanged,
            onAddClicked = stateHolder::onAddTaskClicked
        ) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(8.dp),
        ) {
            TasksList(
                tasks = (stateHolder.state.value as TasksUiState.Content).tasks,
                onStatusChange = stateHolder::onStatusChanged
            )
        }
    }
}

@Composable
fun TasksList(
    tasks: List<Task>,
    onStatusChange: (id: UUID, status: TaskStatus) -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        items(tasks) { task ->
            TaskCard(
                task = task,
                onStatusChange = {
                    onStatusChange(
                        task.id, when (it) {
                            true -> TaskStatus.COMPLETED
                            false -> TaskStatus.ACTIVE
                        }
                    )
                }
            )
        }
    }
}

@Composable
fun TaskCard(
    task: Task,
    onStatusChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxSize()
            .padding(10.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
        Row {
            Checkbox(
                checked = task.status == TaskStatus.COMPLETED,
                modifier = Modifier.align(Alignment.CenterVertically),
                onCheckedChange = onStatusChange,
            )

            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(task.title)
                Text(task.description)
            }
        }
    }
}

@Composable
private fun Input(
    text: String,
    onTextChanged: (String) -> Unit,
    onAddClicked: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChanged,
            modifier = Modifier.weight(weight = 1F),
            label = { Text(text = "Add a task") }
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(onClick = onAddClicked) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }
    }
}