package com.example.mappe1_s349942.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mappe1_s349942.ui.theme.Mappe1_s349942Theme

@Preview(showBackground = true)
@Composable
fun MainPage(modifier: Modifier = Modifier) {
    var currentScreen by rememberSaveable { mutableStateOf((Screen.Onboarding)) }

    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        when (currentScreen) {
            Screen.Onboarding -> Onboarding(
                onStartGameClicked = { currentScreen = Screen.Game },
                onAboutGameClicked = { currentScreen = Screen.About },
                onPreferencesClicked = { currentScreen = Screen.Preferences }
            )

            Screen.Game -> Game()
            Screen.About -> About()
            Screen.Preferences -> Preferences()
        }
    }
}


@Composable
fun Onboarding(
    onStartGameClicked: () -> Unit,
    onAboutGameClicked: () -> Unit,
    onPreferencesClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("LEARN MATH")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onStartGameClicked
        ) {
            Text("START GAME")
        }
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onAboutGameClicked
        ) {
            Text("ABOUT GAME")
        }
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onPreferencesClicked
        ) {
            Text("PREFERENCES")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Mappe1_s349942Theme {
        Onboarding(onStartGameClicked = {}, onAboutGameClicked = {}, onPreferencesClicked = {})
    }
}