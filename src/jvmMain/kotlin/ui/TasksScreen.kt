package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import domain.entity.Task
import presentation.TasksViewModel

@Composable
fun TasksScreen() {
    //val viewModel = TasksViewModel()

    //viewModel.loadData()

    Scaffold(
        topBar = { TabRowTopBar() }
    ) {
        //TasksList(viewModel.state.value)
    }
}

@Composable
fun TabRowTopBar() {
    var state by remember { mutableStateOf(0) }
    val titles = listOf("Active", "Completed")
    TabRow(selectedTabIndex = state) {
        titles.forEachIndexed { index, title ->
            Tab(
                selected = state == index,
                onClick = { state = index },
                text = { Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                ) }
            )
        }
    }
}

@Composable
fun TasksList(tasks: List<Task>) {
    LazyColumn {
        items(tasks) {task ->
            TaskCard(task)
        }
    }
}

@Composable
fun TaskCard(task: Task) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(task.title)
        Text(task.description)
    }
}