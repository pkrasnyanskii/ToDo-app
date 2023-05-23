package ui.screens.tasks_screen.ui_elements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import domain.entity.Task
import domain.entity.TaskStatus

@Composable
fun TaskCard(
    modifier: Modifier,
    task: Task,
    onStatusChange: (Boolean) -> Unit,
    onDeleteClicked: () -> Unit,
    onEditClicked: () -> Unit
) {
    Surface(
        modifier = modifier.clip(shape = CircleShape).alpha(0.95F),
        elevation = 4.dp,
    ) {
        Card(
            elevation = 4.dp,
        ) {
            Row(
                modifier = Modifier
            ) {
                val checked = task.status == TaskStatus.COMPLETED
                CircleCheckbox(
                    checked = checked,
                    modifier = Modifier.align(Alignment.CenterVertically)
                        .alpha(1F),
                    onCheckedChange = { onStatusChange(!checked) },
                )


                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = task.text,
                    fontWeight = FontWeight.W400,
                    modifier = Modifier.weight(1F)
                        .alpha(1F)
                        .align(Alignment.CenterVertically),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(onClick = onEditClicked) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        modifier = Modifier.alpha(1F)
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                IconButton(onClick = onDeleteClicked) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        modifier = Modifier.alpha(1F)
                    )
                }
            }
        }
    }
}