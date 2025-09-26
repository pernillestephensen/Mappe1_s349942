package com.example.mappe1_s349942.ui.pages
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.example.mappe1_s349942.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mappe1_s349942.ui.Skjerm


@Composable
fun StartSkjerm(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(Color(0xFFCFFFE2)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        //Bilde generert av Gemini
        Image(
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp)
                .clip(RoundedCornerShape(55.dp))
        )

        Spacer(modifier = Modifier.height(20.dp))

        //Navigering til "Start spill"
        Button(
            onClick = { navController.navigate(Skjerm.Spill.rute) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2CAB5B),
                contentColor = Color.White
            )
        ) {
            Text(
                text = stringResource(R.string.start_spill_capital),
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        //Navigering til "Om spill"
        Button(
            onClick = { navController.navigate(Skjerm.OmSpillet.rute) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF23B7BA),
                contentColor = Color.White)
            ) {
            Text(
                text = stringResource(R.string.om_spillet_capital),
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        //Navigering til "Preferanser"
        Button(
            onClick = { navController.navigate(Skjerm.Preferanser.rute) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF23B7BA),
                contentColor = Color.White)
            ) {
            Text(
                text = stringResource(R.string.preferanser_capital),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
