package com.levox.game_archive.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthRoleSelectionScreen(
    onUserLoginSelected: () -> Unit,
    onDeveloperLoginSelected: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Выберите вашу роль",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                color = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier.padding(bottom = 48.dp)
        )

        Button(
            onClick = onUserLoginSelected,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Я Пользователь",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Button(
            onClick = onDeveloperLoginSelected,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = TwitchPurple,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Я Разработчик",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview(showBackground = true, name = "Auth Role Selection Light")
@Composable
fun AuthRoleSelectionScreenPreviewLight() {
    MaterialTheme(colorScheme = lightColorScheme()) {
        AuthRoleSelectionScreen(
            onUserLoginSelected = { println("User Login Selected") },
            onDeveloperLoginSelected = { println("Developer Login Selected") }
        )
    }
}

@Preview(showBackground = true, name = "Auth Role Selection Dark")
@Composable
fun AuthRoleSelectionScreenPreviewDark() {
    MaterialTheme(colorScheme = darkColorScheme()) {
        AuthRoleSelectionScreen(
            onUserLoginSelected = { println("User Login Selected") },
            onDeveloperLoginSelected = { println("Developer Login Selected") }
        )
    }
} 