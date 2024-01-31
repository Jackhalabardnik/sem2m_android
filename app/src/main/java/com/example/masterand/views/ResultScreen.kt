package com.example.masterand.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultScreen(
    score: String,
    oldNumberOfColours: String,
    playerName: String,
    onRestartGame: (String, String) -> Unit = { s: String, s1: String -> },
    onLogout: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Results", fontSize = 50.sp, modifier = Modifier.padding(4.dp))
        Text(text = "$playerName - $oldNumberOfColours colors: $score points", fontSize = 30.sp, modifier = Modifier.padding(4.dp))
        Button(onClick = { onRestartGame(oldNumberOfColours, playerName) }) {
            Text(text = "Restart game")
        }
        Button(onClick = onLogout) {
            Text(text = "Logout")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ResultScreenPreview() {
    ResultScreen("4", "5", "test")
}