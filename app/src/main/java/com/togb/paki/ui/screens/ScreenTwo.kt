package com.togb.paki.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.togb.paki.ui.data.PackingItem
import com.togb.paki.ui.model.ListViewModel

@Composable
fun ScreenTwo(
    navController: NavController,
    viewModel: ListViewModel
) {

    var displayedList by remember { mutableStateOf<List<PackingItem>>(emptyList()) }

    LaunchedEffect(viewModel.packingList) {
        displayedList = viewModel.getAllItems()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Cyan)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = {
                    displayedList = viewModel.getAllItems()
                },
                colors = ButtonDefaults.buttonColors(Color.Magenta)
            ) {
                Text(
                    text = "Display All Items"
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    displayedList = viewModel.filterTwoOrMore()
                }
            ) {
                Text(
                    text = "Filter (Qty >= 2)"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(displayedList) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    colors = CardDefaults.cardColors(Color.Gray)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text(text = "Item Name: ${item.name}")
                        Text(text = "Item Name: ${item.category}")
                        Text(text = "Item Name: ${item.quantity}")
                        Text(text = "Item Name: ${item.comments}")
                    }
                }
            }

            if(displayedList.isEmpty()){
                item {
                    Text(
                        text = "No List to Display",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Black)
        ) {
            Text(
                text = "Home Screen"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenTwoPreview() {
    ScreenTwo(
        navController = TODO(),
        viewModel = TODO()
    )
}