package com.example.mappe1_s349942

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mappe1_s349942.ui.pages.MainPage
import com.example.mappe1_s349942.ui.theme.Mappe1_s349942Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mappe1_s349942Theme {
                    MainPage()
            }
        }
    }
}


@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Text(
        text = "LEARN MATH!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Mappe1_s349942Theme {
        Greeting()
    }
}