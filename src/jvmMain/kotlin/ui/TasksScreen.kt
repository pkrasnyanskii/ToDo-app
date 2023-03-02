package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.entity.Task
import domain.usecase.GetTasksUseCase
import org.koin.java.KoinJavaComponent.inject

@Composable
fun TasksScreen() {
    val getTasksUseCase: GetTasksUseCase by inject(GetTasksUseCase::class.java)

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Task List") }) }
    ) {

        TasksList(getTasksUseCase())
    }
}

@Composable
fun TasksList(
    tasks: List<Task>
) {


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