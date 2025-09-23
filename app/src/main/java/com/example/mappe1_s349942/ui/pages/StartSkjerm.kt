package com.example.mappe1_s349942.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StartSkjerm(navController: NavController) {
    Scaffold(
        content = { innerPadding -> // Add paddingValues parameter
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "LÆR MATTE")
                Button(onClick = { navController.navigate("startSpillet") }) {
                    Text("START SPILLET")
                }
                Button(onClick = { navController.navigate("omSpillet") }) {
                    Text("OM SPILLET")
                }
                Button(onClick = { navController.navigate("preferanser") }) {
                    Text("PREFERANSER")
                }
            }
        }
    )
}