package com.levox.game_archive

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.levox.game_archive.auth.AuthRoleSelectionScreen
import com.levox.game_archive.auth.AuthScreen
import com.levox.game_archive.auth.AuthViewModel
import com.levox.game_archive.domain.repository.AuthRepository
import com.levox.game_archive.ui.theme.GameArchiveTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var repo: AuthRepository

    private val twitchAuthUrl = "${BuildConfig.AUTH_BASE_URL}authorize" +
            "?response_type=token" +
            "&client_id=${BuildConfig.CLIENT_ID}" +
            "&redirect_uri=${BuildConfig.REDIRECT_URI}" +
            "&scope="

    private val viewModel: AuthViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            GameArchiveTheme {
                NavHost(navController = navController, startDestination = "auth_selection") {
                    composable("auth_selection") {
                        AuthRoleSelectionScreen(
                            onUserLoginSelected = { /*navController.navigate("login")*/ },
                            onDeveloperLoginSelected = { navController.navigate("auth") }
                        )
                    }

                    composable("auth") { AuthScreen(viewModel) }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        println("onNewIntent: ${intent.data}")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button(onClick) { Text("Log in") }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GameArchiveTheme {
        Greeting("Android") {}
    }
}