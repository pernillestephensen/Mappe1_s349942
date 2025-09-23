package com.example.mappe1_s349942.ui.pages

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mappe1_s349942.viewmodels.PrefViewModel

@Composable
fun Preferanser(navController: NavController) {
    val app = androidx.compose.ui.platform.LocalContext.current.applicationContext as Application
    val prefVm: PrefViewModel = viewModel(factory = androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.getInstance(app))

    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Top) {
        Text("Velg antall oppgaver per spill:", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(12.dp))

        val options = listOf(5,10,15)
        options.forEach { n ->
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                RadioButton(selected = prefVm.antall.value == n, onClick = { prefVm.settAntall(n) })
                Spacer(modifier = Modifier.width(8.dp))
                Text("$n oppgaver")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Lagre og tilbake")
        }
    }
}
