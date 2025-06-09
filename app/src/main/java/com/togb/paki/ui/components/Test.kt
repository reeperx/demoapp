package com.togb.paki.ui.components

import android.util.Log
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
import com.togb.paki.ui.theme.PakiTheme

@Composable
fun Test() {

    var showAddItemDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MyButton(onClick = { showAddItemDialog = true })

        if (showAddItemDialog) {
            AddItemDialog(
                onAddItem = { newItem ->
                    Log.d("DialogPopUp", "New item added: $newItem")
                },
                onDismiss = {
                    showAddItemDialog = false
                })
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TestPreview() {
    PakiTheme {
        Test()
    }
}