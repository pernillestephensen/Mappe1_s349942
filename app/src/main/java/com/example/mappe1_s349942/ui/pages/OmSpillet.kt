package com.example.mappe1_s349942.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun OmSpillet(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Top) {
        Text("Om spillet", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(12.dp))
        Text("Dette er et mattespill for barn. Spillet består av multiplikasjon (+), subtraksjon (-) og divisjon (/) av heltall. " +
                "Trykk på tallene for å skrive inn svar. Farge og ikoner gir tilbakemelding.")
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB523BA),
                contentColor = Color.White
            )) {
            Text("Tilbake")
        }
    }
}

@Composable
@Preview
fun OmSpillet() {
    OmSpillet(navController = NavController(LocalContext.current))
}
