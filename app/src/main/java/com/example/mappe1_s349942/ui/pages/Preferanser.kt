package com.example.mappe1_s349942.ui.pages

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mappe1_s349942.viewmodels.PrefViewModel
import com.example.mappe1_s349942.R

@Composable
fun Preferanser(navController: NavController) {
    val app = androidx.compose.ui.platform.LocalContext.current.applicationContext as Application
    val prefVm: PrefViewModel =
        viewModel(factory = androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.getInstance(app))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.antall_oppgaver),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        //Alternativer for antall oppgaver
        val options = listOf(5, 10, 15)
        options.forEach { n ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(vertical = 8.dp)
            ) {
                RadioButton(
                    selected = prefVm.antall.value == n,
                    onClick = { prefVm.settAntall(n) },
                    colors = RadioButtonDefaults.colors(Color(0xFF23B7BA))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("$n " + stringResource(R.string.oppgaver))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth(0.7f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF23B7BA),
                contentColor = Color.White
            )
        ) {
            Text(stringResource(R.string.tilbake))
        }
    }
}

