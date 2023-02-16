package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import domain.entity.Task
import org.koin.java.KoinJavaComponent.inject
import presentation.TasksViewModel

@Composable
fun TasksScreen() {
    val viewModel: TasksViewModel by inject(TasksViewModel::class.java)

    viewModel.loadData()

    Scaffold(
        topBar = { TabRowTopBar() }
    ) {
        TasksList(viewModel.state.value)
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
    Card(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(task.title)
            Text(task.description)
        }
    }
}