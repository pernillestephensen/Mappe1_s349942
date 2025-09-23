package com.example.mappe1_s349942.ui.viewmodels

import android.app.Application
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mappe1_s349942.R

class SpillViewModel (application: Application): AndroidViewModel(application) {
    val oppgaver = application.resources.getStringArray(R.array.oppgaver)
    fun hentOppgave(): String {
        return oppgaver.random()
    }

    @Composable
    fun AntallSpm(modifier: Modifier = Modifier) {
        val prefViewModel: PrefViewModel = viewModel()
        var felt by remember { mutableStateOf("") }
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Velg antall regnestykker:")
            Button(onClick = { prefViewModel.settPref(felt) }) {
                Text(text = "5")
            }
            Button(onClick = { prefViewModel.settPref(felt) }) {
                Text(text = "10")
            }
            Button(onClick = { prefViewModel.settPref(felt) }) {
                Text(text = "15")
            }
        }
    }
}