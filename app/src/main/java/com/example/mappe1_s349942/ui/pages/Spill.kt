package com.example.mappe1_s349942.ui.pages

import android.app.Application
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mappe1_s349942.viewmodels.PrefViewModel
import com.example.mappe1_s349942.viewmodels.SpillViewModel

@Composable
fun Spill(navController: NavController) {
    val context = LocalContext.current
    val app = context.applicationContext as Application

    val prefVm: PrefViewModel = viewModel(factory = androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.getInstance(app))
    val spillVm: SpillViewModel = viewModel(factory = androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.getInstance(app))

    // lokale state for exit-dialog
    var showExitDialog by remember { mutableStateOf(false) }

    // Start game with preference hvis ikke startet
    LaunchedEffect(key1 = prefVm.antall.value) {
        // start game når Composable mountes
        spillVm.startSpill(prefVm.antall.value)
    }

    BackHandler {
        // hvis ikke ferdig, vis dialog
        if (!spillVm.spillOver.value) {
            showExitDialog = true
        } else {
            navController.popBackStack()
        }
    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text("Avslutt?") },
            text = { Text("Vil du avslutte spillet? Fremdrift vil ikke bli lagret.") },
            dismissButton = {
                TextButton(onClick = { showExitDialog = false }) { Text("Nei") }
            },
            confirmButton = {
                TextButton(onClick = {
                    showExitDialog = false
                    spillVm.avsluttSpill()
                    navController.popBackStack()
                }) { Text("Ja") }
            }
        )
    }

    // Hoved-UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Topp/header: antall igjen / progress
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Spørsmål: ${spillVm.spmIndeks.value + 1}/${prefVm.antall.value}", fontWeight = FontWeight.Bold)
            Text(text = "Igjen: ${spillVm.remaining()}", fontWeight = FontWeight.Bold)
        }


        // Task display
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(Color(0xFF319BEB), RoundedCornerShape(40.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (spillVm.spillOver.value) {
                Text("Spillet er ferdig. Superbra jobba!", fontSize = 35.sp)
            } else {
                Text(text = spillVm.oppgaveTekst.value, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
            }
        }

        // Input og tilbakemelding
        Column {
            Box(modifier = Modifier.fillMaxWidth().height(56.dp), contentAlignment = Alignment.Center) {
                Text(text = spillVm.inputText.value.ifEmpty { "HVA ER SVARET?" }, fontSize = 35.sp, fontWeight = FontWeight.Bold)
            }
            spillVm.tilbakemelding.value?.let { fb ->
                when {
                    fb == "riktig" -> {
                        Box(modifier = Modifier.fillMaxWidth().height(56.dp), contentAlignment = Alignment.Center){
                            Text("Riktig svar! Kjempebra jobba",
                                color = Color(0xFF2E7D32),
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                fontSize = 25.sp)
                    }
                }
                    fb.startsWith("feil:") -> {
                        val parts = fb.split(":")
                        val correct = if (parts.size > 1) parts[1] else "?"
                        Box(modifier = Modifier.fillMaxWidth().height(56.dp), contentAlignment = Alignment.Center) {
                            Text("Feil — riktig svar er $correct. Prøv igjen, dette klarer du!",
                                color = Color(0xFFB00020),
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                fontSize = 25.sp)
                        }
                    }
                    fb == "tomt" -> {
                        Text("Skriv inn et svar først!", color = Color(0xFFB00020))
                    }
                }
            }
        }


        // Nummerknapper (0-9) i grid 3x4 (0 nederst)
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            val rows = listOf(listOf(1,2,3), listOf(4,5,6), listOf(7,8,9), listOf(-1,0,-2))
            rows.forEach { row ->
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp))
                {
                    row.forEach { v ->
                        when (v) {
                            -2 -> Button(
                                onClick = { spillVm.sendInnSvar() },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF004015),
                                    contentColor = Color.White)

                                ) {
                                Text("Svar")
                            }
                            -1 -> Button(
                                onClick = { spillVm.slettTall() },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFE53935), // rød knapp
                                    contentColor = Color.White)
                            ){
                                Text("Slett")
                            }
                            else -> Button(
                                onClick = { spillVm.leggTilTall(v) },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF002440),
                                    contentColor = Color.White)) {
                                Text("$v", )
                            }
                        }
                    }
                }
            }
        }

        // Nederst: OK / Neste / Avslutt
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    // Etter sjekk: gå videre. Hvis ingen flere -> vis gameOver melding
                    spillVm.nesteSpm()
                },
                modifier = Modifier.weight(2f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2C7FE8),
                    contentColor = Color.White
                )) {
                Text("Neste oppgave")
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(onClick = {
                showExitDialog = true
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFB5D1E8),
                contentColor = Color.White
            )) {
                Text("Avslutt")
            }
        }
    }
}



