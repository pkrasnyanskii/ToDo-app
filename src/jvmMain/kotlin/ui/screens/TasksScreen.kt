package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import domain.entity.Task
import domain.entity.TaskStatus
import presentation.TasksStateHolder
import presentation.TasksUiState
import java.util.*

@Composable
fun TasksScreen(onEditClicked: (UUID, String) -> Unit) {
    val stateHolder = remember { TasksStateHolder() }

    stateHolder.loadData()

    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.background(MaterialTheme.colors.primary)
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Task List",
                    modifier = Modifier.weight(0.5F),
                    color = MaterialTheme.colors.onPrimary
                )
                NetworkButtons(
                    stateHolder::onReceiveDataButtonClicked,
                    stateHolder::onSendDataButtonClicked
                )
            }


        },
        bottomBar = {
            Input(
                modifier = Modifier.padding(20.dp),
                text = (stateHolder.state.value as TasksUiState.Content).inputText,
                onTextChanged = stateHolder::onInputTextChanged,
                onAddClicked = stateHolder::onAddTaskClicked
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TasksList(
                modifier = Modifier.padding(bottom = 8.dp)
                    .fillMaxSize(),
                tasks = (stateHolder.state.value as TasksUiState.Content).tasks,
                onStatusChange = stateHolder::onStatusChanged,
                onDeleteClicked = stateHolder::onTaskDeleteClicked,
                onEditClicked = onEditClicked
            )
        }
    }
}

@Composable
fun WhiteButton(
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        content = content,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Color.Black
        )
    )
}

@Composable
fun NetworkButtons(
    onReceiveButtonClicked: () -> Unit,
    onSendButtonClicked: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth(0.5F)
    ) {
        WhiteButton(onClick = onSendButtonClicked) {
            Text(text = "Commit Tasks")
        }

        Spacer(modifier = Modifier.width(8.dp))

        WhiteButton(onClick = onReceiveButtonClicked) {
            Text(text = "Update Tasks")
        }
    }
}

@Composable
fun TasksList(
    modifier: Modifier,
    tasks: List<Task>,
    onStatusChange: (UUID, status: TaskStatus) -> Unit,
    onDeleteClicked: (UUID) -> Unit,
    onEditClicked: (UUID, String) -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        items(tasks) { task ->
            TaskCard(
                modifier = modifier,
                task = task,
                onStatusChange = {
                    onStatusChange(
                        task.id, when (it) {
                            true -> TaskStatus.COMPLETED
                            false -> TaskStatus.ACTIVE
                        }
                    )
                },
                onDeleteClicked = { onDeleteClicked(task.id) },
                onEditClicked = { onEditClicked(task.id, task.text) }
            )
        }
    }
}

@Composable
fun TaskCard(
    modifier: Modifier,
    task: Task,
    onStatusChange: (Boolean) -> Unit,
    onDeleteClicked: () -> Unit,
    onEditClicked: () -> Unit
) {
    Card(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
        Row {
            Checkbox(
                checked = task.status == TaskStatus.COMPLETED,
                modifier = Modifier.align(Alignment.CenterVertically),
                onCheckedChange = onStatusChange,
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = task.text,
                modifier = Modifier.weight(1F)
                    .align(Alignment.CenterVertically),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = onEditClicked) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = onDeleteClicked) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun Input(
    modifier: Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    onAddClicked: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
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