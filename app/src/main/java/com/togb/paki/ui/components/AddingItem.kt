package com.togb.paki.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.togb.paki.ui.data.PackingItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddingItem(
    onDismiss: () -> Unit, // CLosing AlertDialog
    onAddItem: (PackingItem) -> Unit // Adding Items
) {
    // Variables for capturing data
    // affected by state since data changes from user input
    var itemName by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") } // Initially its going to be a string
    var comments by remember { mutableStateOf("") }

    // Variables for displaying error states
    // Will onl display if the fields are not valid or validation fails
    var itemNameError by remember { mutableStateOf<String?>(null) } // Output: "Please fill out ItemName"
    var categoryError by remember { mutableStateOf<String?>(null) }
    var quantityError by remember { mutableStateOf<String?>(null) }
    var commentsError by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
    ) {
        Surface {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                // ItemName TextField
                // Captures user item name
                OutlinedTextField(
                    value = itemName,
                    onValueChange = {
                        itemName = it;
                        itemNameError = null
                    },
                    label = { Text("Add Item") },
                    isError = itemNameError != null, // Display Error if any or "null/nothing" if no error
                    modifier = Modifier
                        .fillMaxWidth()
                )

                // Display the error message ("itemName") to the user
                itemNameError?.let {
                    Text(
                        it, // holds data from "itemName"
                        color = MaterialTheme.colorScheme.error
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                // Category TextField
                // Captures user item name
                OutlinedTextField(
                    value = category,
                    onValueChange = {
                        category = it;
                        categoryError = null
                    },
                    label = { Text("Add Category") },
                    isError = categoryError != null, // Display Error if any or "null/nothing" if no error
                    modifier = Modifier
                        .fillMaxWidth()

                )
                // Display the error message ("category") to the user
                categoryError?.let {
                    Text(
                        it, // holds data from "itemName"
                        color = MaterialTheme.colorScheme.error
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .padding(4.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {}
                    ) {
                        Text("Close")
                    }
                    Button(
                        onClick = {
                            // Reset form errors before checking if data is correct
                            itemNameError = null
                            categoryError = null
                            quantityError = null
                            commentsError = null

                            var isValid = true

                            // check if itemName is empty
                            if (itemName.isBlank()) {
                                itemNameError = "Name Field is Empty"
                                isValid = false
                            }

                            // check if category is empty
                            if (category.isBlank()) {
                                categoryError = "Category Field is Empty"
                                isValid = false
                            }

                            // check if all fields are filled-up
                            if (isValid) {
                                onAddItem(
                                    PackingItem(
                                        name = itemName,
                                        category = category,
                                        quantity = 10,
                                        comments = comments
                                    )
                                )
                                onDismiss() // Closes automatically after adding items
                            }
                        },
                        colors = ButtonDefaults.buttonColors(Color.Green)
                    ) {
                        Text("Add")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddingItemPreview() {
    AddingItem(
        onDismiss = {},
        onAddItem = {}
    )
}