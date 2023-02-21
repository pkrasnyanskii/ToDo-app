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
import domain.usecase.GetActiveTasksUseCase
import domain.usecase.GetCompletedTasksUseCase
import org.koin.java.KoinJavaComponent.inject
import presentation.TasksViewModel

class TasksScreen {

    private val getActiveTasksUseCase: GetActiveTasksUseCase by inject(GetActiveTasksUseCase::class.java)
    private val getCompletedTasksUseCase: GetCompletedTasksUseCase by inject(GetCompletedTasksUseCase::class.java)


    @Composable
    fun TasksScreen() {
        //val viewModel: TasksViewModel by inject(TasksViewModel::class.java)
        //val viewModel = remember { mutableStateOf(TasksViewModel::class.java) }

        var state = remember { mutableStateOf(0) }

        //val a = viewModel.value


        Scaffold(
            topBar = { TabRowTopBar(state) }
        ) {
            val tasks: List<Task> = when(state.value) {
                0 -> getActiveTasksUseCase()
                1 -> getCompletedTasksUseCase()
                else -> throw Error("wrong tab index")
            }
            TasksList(tasks)
        }
    }

    @Composable
    fun TabRowTopBar(state: MutableState<Int>) {

        val titles = listOf("Active", "Completed")
        TabRow(selectedTabIndex = state.value) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state.value == index,
                    onClick = { state.value = index },
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
}