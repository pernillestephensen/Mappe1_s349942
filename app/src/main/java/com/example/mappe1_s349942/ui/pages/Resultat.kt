package com.example.mappe1_s349942.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mappe1_s349942.R
import com.example.mappe1_s349942.ui.Skjerm

@Composable
fun ResultatSkjerm(
    navController: NavController,
    riktige: Int,
    totalt: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.spill_ferdig_tittel),
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.spill_ferdig_resultat, riktige, totalt),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = {
                navController.navigate(Skjerm.Spill.rute) {
                    popUpTo(Skjerm.Start.rute) { inclusive = false }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF59BA89))
        ) {
            Text(stringResource(R.string.spill_igjen))
        }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate(Skjerm.Start.rute) {
                popUpTo(Skjerm.Start.rute) { inclusive = true }
            } },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935))
        ) {
            Text(stringResource(R.string.avslutt))
        }
    }
}
