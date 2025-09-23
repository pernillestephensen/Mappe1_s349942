package com.example.mappe1_s349942.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun OmSpillet(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Top) {
        Text("Om spillet", style = androidx.compose.material3.MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(12.dp))
        Text("Dette er et enkelt addisjonsspill for å øve hoderegning. Trykk på tallene for å skrive svar. Farge og ikoner gir tilbakemelding.")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Tilbake")
        }
    }
}
