package ui.screens.tasks_screen.ui_elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun Input(
    modifier: Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    onAddClicked: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
            .clip(shape = CircleShape)
            .alpha(0.95F)
            .background(Color.White)
    ) {
        TextField(
            value = text,
            textStyle = TextStyle(fontSize = 16.sp),
            onValueChange = onTextChanged,
            modifier = Modifier.weight(1F)
                .alpha(1F)
                .align(Alignment.CenterVertically),
            label = null,
            shape = MaterialTheme.shapes.small,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor =  Color.Transparent, //hide the indicator
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedLabelColor = Color.Transparent,
                cursorColor = Color.DarkGray
            )
        )

        //Spacer(modifier = Modifier.width(8.dp))

        IconButton(onClick = onAddClicked) {
            Icon(
                imageVector = Icons.Outlined.Send,
                modifier = Modifier.alpha(1F),
                contentDescription = null
            )
        }
    }
}