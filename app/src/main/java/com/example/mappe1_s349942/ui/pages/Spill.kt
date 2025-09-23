package com.example.mappe1_s349942.ui.pages

import android.app.Application
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mappe1_s349942.viewmodels.SpillViewModel
import com.example.mappe1_s349942.viewmodels.PrefViewModel
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

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
            title = { Text("Avslutte spillet?") },
            text = { Text("Vil du avslutte spillet? Fremdrift vil ikke bli lagret.") },
            confirmButton = {
                TextButton(onClick = {
                    showExitDialog = false
                    spillVm.avsluttSpill()
                    navController.popBackStack()
                }) { Text("Ja") }
            },
            dismissButton = {
                TextButton(onClick = { showExitDialog = false }) { Text("Nei") }
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
        // Top: antall igjen / progress
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Spørsmål: ${spillVm.spmIndeks.value + 1}/${prefVm.antall.value}", fontWeight = FontWeight.Bold)
            Text(text = "Igjen: ${spillVm.remaining()}", style = MaterialTheme.typography.bodySmall)
        }

        // Task display
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (spillVm.spillOver.value) {
                Text("Ingen flere oppgaver. Bra jobba!", fontSize = 20.sp)
            } else {
                Text(text = spillVm.oppgaveTekst.value, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
            }
        }

        // Input og tilbakemelding
        Column {
            Box(modifier = Modifier.fillMaxWidth().height(56.dp), contentAlignment = Alignment.Center) {
                Text(text = spillVm.inputText.value.ifEmpty { "Svar" }, fontSize = 28.sp)
            }
            spillVm.tilbakemelding.value?.let { fb ->
                when {
                    fb == "correct" -> {
                        Text("Riktig!", color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
                    }
                    fb.startsWith("wrong:") -> {
                        val parts = fb.split(":")
                        val correct = if (parts.size > 1) parts[1] else "?"
                        Text("Feil — riktig svar er $correct", color = Color(0xFFB00020))
                    }
                    fb == "empty" -> {
                        Text("Skriv inn et svar først!", color = Color(0xFFB00020))
                    }
                }
            }
        }

        // Nummerknapper (0-9) i grid 3x4 (0 nederst)
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            val rows = listOf(listOf(1,2,3), listOf(4,5,6), listOf(7,8,9), listOf(-1,0,-2))
            rows.forEach { row ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    row.forEach { v ->
                        when (v) {
                            -1 -> Box(modifier = Modifier.weight(1f)) {} // tom
                            -2 -> Button(onClick = { spillVm.slettTall() }, modifier = Modifier.weight(1f)) {
                                Text("Slett")
                            }
                            else -> Button(onClick = { spillVm.leggTilTall(v) }, modifier = Modifier.weight(1f)) {
                                Text("$v")
                            }
                        }
                    }
                }
            }
        }

        // Nederst: OK / Neste / Avslutt
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { spillVm.sendInnSvar() }) {
                Text("OK")
            }
            Button(onClick = {
                // Etter sjekk: gå videre. Hvis ingen flere -> vis gameOver melding
                spillVm.nesteSpm()
            }) {
                Text("Neste")
            }
            Button(onClick = {
                showExitDialog = true
            }) {
                Text("Avslutt")
            }
        }
    }
}

//@Composable
//fun Spill(navController: NavController){
//    val minViewModel : SpillViewModel= viewModel()
//    minViewModel.AntallSpm()
//    val tekst=minViewModel.hentOppgave()
//    Column(modifier=Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(text = tekst,)
//    }
//}


