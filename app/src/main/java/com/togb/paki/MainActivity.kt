package com.togb.paki

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.togb.paki.ui.components.AddItemDialog
import com.togb.paki.ui.components.MyButton
import com.togb.paki.ui.theme.PakiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PakiTheme {
                PackingListScreen()
            }
        }
    }
}


@Composable
fun PackingListScreen() {
    // State to control whether the AddItemDialog should be shown or hidden.
    var showAddItemDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MyButton(onClick = { showAddItemDialog = true })

        // Conditional rendering of the AddItemDialog.
        // It will only be composed (and thus displayed) when 'showAddItemDialog' is true.
        if (showAddItemDialog) {
            AddItemDialog(
                onAddItem = { newItem ->
                    Log.d("PackingListScreen", "New item added: $newItem")
                },
                onDismiss = {
                    showAddItemDialog = false
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PackingListScreenPreview() {
    PakiTheme {
        PackingListScreen()
    }
}
