package ui.screens.tasks_screen.ui_elements

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CircleCheckbox(
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: () -> Unit,
    modifier: Modifier = Modifier
) {

    val color = MaterialTheme.colors
    val imageVector = if (checked) Icons.Filled.CheckCircle else Icons.Outlined.Circle
    //val tint = if (checked) color.primary.copy(alpha = 0.8f) else Color.White.copy(alpha = 0.8f)
    val background = if (checked) Color.White else Color.Transparent

    IconButton(onClick = { onCheckedChange() },
        modifier = modifier,
        enabled = enabled) {

        Icon(imageVector = imageVector,
            modifier = Modifier.background(background, shape = CircleShape),
            contentDescription = "checkbox")
    }
}
