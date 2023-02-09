package ui

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun TasksScreen() {
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
