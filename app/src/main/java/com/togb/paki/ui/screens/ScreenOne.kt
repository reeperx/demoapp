package com.togb.paki.ui.screens


import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.togb.paki.ui.components.AddItemDialog
import com.togb.paki.ui.model.ListViewModel
import com.togb.paki.ui.theme.PakiTheme

@Composable
fun ScreenOne(
    navController: NavController
) {
    val context = LocalContext.current
    val activity = ( context as? Activity)

    var showAddItemDialog by remember { mutableStateOf(false) }

    if(showAddItemDialog){
        AddItemDialog(
            onAddItem = { newItem ->
                showAddItemDialog = false
            },
            onDismiss = { showAddItemDialog = false }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Cyan)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = {
                showAddItemDialog = true
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Black)
        ) {
            Text(
                text = "Add to Packing List"
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate("screen_two")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Magenta)
        ) {
            Text(
                text = "Show Packing List"
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                activity?.finish()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text(
                text = "Exit"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenOnePreview() {
    PakiTheme {
        ScreenOne(
            navController = TODO()
        )
    }
}