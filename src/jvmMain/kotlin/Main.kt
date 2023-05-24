import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.compose.AppTheme
import di.data
import di.domain
import di.presentation
import org.koin.core.context.GlobalContext.startKoin
import ui.Root

@Composable
@Preview
fun App() {
    /*MaterialTheme {
        Root()
    }*/
    AppTheme {
        Root()
    }
}

fun main() {
    startKoin {
        modules(
            data,
            domain,
            presentation
        )
    }

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "PlannerScape",
            icon = painterResource("drawable/icon.svg")
        ) {
            App()
        }
    }
}
