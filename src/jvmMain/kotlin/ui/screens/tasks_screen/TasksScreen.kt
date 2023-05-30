package ui.screens.tasks_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domain.entity.Task
import domain.entity.TaskStatus
import presentation.tasks.TasksStateHolder
import presentation.tasks.TasksUiState
import ui.screens.tasks_screen.ui_elements.Input
import ui.screens.tasks_screen.ui_elements.TaskCard
import ui.screens.tasks_screen.ui_elements.WhiteRoundButton
import java.util.*

@Composable
fun TasksScreen(onEditClicked: (Task) -> Unit) {
    val stateHolder = remember { TasksStateHolder() }

    stateHolder.loadData()

    Row {
        Image(
            painterResource("drawable/TaskListLeftBarImage.png"),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.weight(0.25F).fillMaxHeight()
        )
        Box(
            modifier = Modifier.weight(0.75F).background(Color(255, 235, 230))
        ) {
            Image(
                painterResource("drawable/TaskListBackgroundImage.png"),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.matchParentSize()
            )
            /*Box(
                modifier = Modifier.fillMaxSize()
                    .paint(
                        painterResource("drawable/TaskListBackgroundImage.png"),
                        contentScale = ContentScale.FillBounds
                    )
            )*/
            Scaffold(
                backgroundColor = Color.Transparent,
                topBar = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.background(Color.Transparent)
                            .padding(horizontal = 20.dp)
                            .padding(top = 10.dp)
                    ) {
                        Text(
                            text = "Task List",
                            fontSize = 36.sp,
                            modifier = Modifier.weight(0.5F),
                            color = Color.DarkGray
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
                    Spacer(modifier = Modifier.height(48.dp))
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
    }
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
        WhiteRoundButton(onClick = onSendButtonClicked) {
            Text(text = "Send")
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Upload,
                contentDescription = null
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        WhiteRoundButton(onClick = onReceiveButtonClicked) {
            Text(text = "Receive")
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Download,
                contentDescription = null
            )
        }
    }
}

@Composable
fun TasksList(
    modifier: Modifier,
    tasks: List<Task>,
    onStatusChange: (UUID, status: TaskStatus) -> Unit,
    onDeleteClicked: (UUID) -> Unit,
    onEditClicked: (Task) -> Unit
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
                onEditClicked = { onEditClicked(task) }
            )
        }
    }
}


