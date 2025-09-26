package com.example.mappe1_s349942.ui.pages

import android.app.Application
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mappe1_s349942.viewmodels.PrefViewModel
import com.example.mappe1_s349942.viewmodels.SpillViewModel
import com.example.mappe1_s349942.R
import com.example.mappe1_s349942.ui.Skjerm

@Composable
fun Spill(navController: NavController) {
    val context = LocalContext.current
    val app = context.applicationContext as Application

    val prefVm: PrefViewModel = viewModel(
        factory = androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.getInstance(app)
    )
    val spillVm: SpillViewModel = viewModel(
        factory = androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.getInstance(app)
    )

    val current = (spillVm.spmIndeks.value + 1).coerceAtMost(prefVm.antall.value)


    var showExitDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = prefVm.antall.value) {
        spillVm.startSpill(prefVm.antall.value)
    }

    BackHandler {
        if (!spillVm.spillOver.value) {
            showExitDialog = true
        } else {
            navController.popBackStack()
        }
    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text(stringResource(R.string.avslutt_spmtegn)) },
            text = { Text(stringResource(R.string.avslutt_popup)) },
            dismissButton = {
                TextButton(onClick = {
                    showExitDialog = false
                }) { Text(stringResource(R.string.nei)) }
            },
            confirmButton = {
                TextButton(onClick = {
                    showExitDialog = false
                    spillVm.avsluttSpill()
                    navController.navigate(Skjerm.Start.rute) {
                        popUpTo(Skjerm.Start.rute) { inclusive = true }
                    }
                }) { Text(stringResource(R.string.ja)) }
            }
        )
    }
    // Hoved UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        //Antall oppgaver og oppgaver som gjenstår (e.g. Spørsmål: 4/10 Igjen: 6)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color(0xFFD4EAFF), RoundedCornerShape(30.dp))
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.sporsmal) + " $current/${prefVm.antall.value}",
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(R.string.igjen) + " ${spillVm.remaining()}",
                fontWeight = FontWeight.Bold
            )
        }

        // Oppgaveboks
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(Color(0xFFD4EAFF), RoundedCornerShape(30.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = spillVm.oppgaveTekst.value,
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }

        // Input fra bruker og tilbakemelding
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = spillVm.inputText.value.ifEmpty { stringResource(R.string.skriv_inn_svar) },
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            spillVm.tilbakemelding.value?.let { fb ->
                when {
                    fb == "riktig" -> {
                        Box(
                            modifier = Modifier.fillMaxWidth().height(40.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                stringResource(R.string.riktig_svar),
                                color = Color(0xFF2E7D32),
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp
                            )
                        }
                    }

                    fb.startsWith("feil:") -> {
                        Box(
                            modifier = Modifier.fillMaxWidth().height(40.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                stringResource(R.string.feil_svar),
                                color = Color(0xFFB00020),
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                fontSize = 18.sp
                            )
                        }
                    }

                    fb == "tomt" -> {
                        Box(
                            modifier = Modifier.fillMaxWidth().height(40.dp),
                            contentAlignment = Alignment.Center
                        ){
                        Text(
                            stringResource(R.string.skriv_svar_melding),
                            color = Color(0xFFB00020),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        }


        // Nummerknapper (0-9) i grid 3x4
        Column(
            modifier = Modifier.fillMaxWidth().padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val rows = listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9), listOf(-1, 0, -2))
            rows.forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                )
                {
                    row.forEach { v ->
                        when (v) {
                            -2 -> Button( //svar-knapp
                                onClick = { spillVm.sendInnSvar() },
                                modifier = Modifier.weight(1f).padding(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF59BA89),
                                    contentColor = Color.White
                                )

                            ) {
                                Text(stringResource(R.string.svar))
                            }
                            -1 -> Button( //slett-knapp
                                onClick = { spillVm.slettTall() },
                                modifier = Modifier.weight(1f).padding(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFE53935),
                                    contentColor = Color.White
                                )
                            ) {
                                Text(stringResource(R.string.slett))
                            }

                            else -> Button( //alle tallene
                                onClick = { spillVm.leggTilTall(v) },
                                modifier = Modifier.weight(1f).padding(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF5899D1),
                                    contentColor = Color.White
                                )
                            ) {
                                Text("$v",)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        //Knapp for neste oppgave
        Row(modifier = Modifier.fillMaxWidth().padding(15.dp)) {
            Button(
                onClick = { spillVm.nesteSpm() },
                modifier = Modifier.weight(2f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF93BDC7),
                    contentColor = Color.White
                )
            ) {
                Text(stringResource(R.string.neste_oppgave))
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        //Knapp for avslutt spill (viser exit-popup)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { showExitDialog = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFED5C5C),
                    contentColor = Color.White
                )
            ) {
                Text(stringResource(R.string.avslutt))
            }
        }

        //Navigerer til side som viser score
        LaunchedEffect(spillVm.spillOver.value) {
            if (spillVm.spillOver.value && !spillVm.avsluttetManuelt.value) {
                navController.navigate(
                    Skjerm.Resultat.lagRute(spillVm.riktige.value, prefVm.antall.value)
                )
            }
        }

    }
}



