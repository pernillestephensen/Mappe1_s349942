package com.example.mappe1_s349942

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mappe1_s349942.ui.Skjerm
import com.example.mappe1_s349942.ui.pages.OmSpillet
import com.example.mappe1_s349942.ui.pages.Preferanser
import com.example.mappe1_s349942.ui.pages.Spill
import com.example.mappe1_s349942.ui.pages.StartSkjerm
import com.example.mappe1_s349942.ui.theme.Mappe1_s349942Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mappe1_s349942Theme {
                Surface(color = MaterialTheme.colorScheme.background){
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "startSkjerm")
                    {
                        composable(Skjerm.Start.rute){StartSkjerm(navController)}
                        composable(Skjerm.Spill.rute){Spill(navController)}
                        composable(Skjerm.OmSpillet.rute){OmSpillet(navController)}
                        composable(Skjerm.Preferanser.rute){Preferanser(navController)}
                    }
                }
            }
        }
    }
}

//@Composable
//fun MyApp(modifier: Modifier = Modifier){
//    val navController = rememberNavController()
//    NavigationGraph(navController = navController)
//}
//
//@Composable
//fun NavigationGraph(navController: NavHostController) {
//    NavHost(navController  = navController, startDestination="startSkjerm")
//    {
//        composable ("startSkjerm"){
//            StartSkjerm(navController = navController)
//        }
//        composable("startSpillet"){
//            Spill(navController: NavController)
//        }
//        composable("omSpillet"){
//            OmSpillet(navController = navController)
//        }
//        composable("preferanser"){
//            Preferanser(navController = navController)
//        }
//    }
//}


