package com.example.mappe1_s349942.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mappe1_s349942.ui.viewmodels.SpillViewModel

@Composable
fun Spill(navController: NavController){
    val minViewModel : SpillViewModel= viewModel()
    minViewModel.AntallSpm()
    val tekst=minViewModel.hentOppgave()
    Column(modifier=Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = tekst,)
    }
}


