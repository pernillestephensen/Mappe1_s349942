package com.example.mappe1_s349942.ui.pages
import androidx.compose.foundation.Image
import com.example.mappe1_s349942.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mappe1_s349942.ui.Skjerm

@Composable
fun StartSkjerm(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.app_icon), contentDescription = null, modifier = Modifier.size(120.dp))

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { navController.navigate(Skjerm.Spill.rute) }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Start spill", style = MaterialTheme.typography.titleMedium)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = { navController.navigate(Skjerm.OmSpillet.rute) }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Om spillet")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = { navController.navigate(Skjerm.Preferanser.rute) }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Preferanser")
        }
    }
}
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun StartSkjerm(navController: NavController) {
//    Scaffold(
//        content = { innerPadding -> // Add paddingValues parameter
//            Column(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(text = "LÆR MATTE")
//                Button(onClick = { navController.navigate("startSpillet") }) {
//                    Text("START SPILLET")
//                }
//                Button(onClick = { navController.navigate("omSpillet") }) {
//                    Text("OM SPILLET")
//                }
//                Button(onClick = { navController.navigate("preferanser") }) {
//                    Text("PREFERANSER")
//                }
//            }
//        }
//    )
//}