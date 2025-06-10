package com.togb.paki.ui.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.togb.paki.ui.data.PackingItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemDialog(
    onAddItem: (PackingItem) -> Unit,
    onDismiss: () -> Unit
) {

    var itemName by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var comments by remember { mutableStateOf("") }

    var itemNameError by remember { mutableStateOf<String?>(null) }
    var categoryError by remember { mutableStateOf<String?>(null) }
    var quantityError by remember { mutableStateOf<String?>(null) }


    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                textDecoration = TextDecoration.None,
                text = "Add New Packing Item"
            )
        },
        text = {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = itemName,
                    onValueChange = {
                        itemName = it
                        itemNameError = null
                        Log.d("AddItemDialog", "Item Name changed to: $itemName")
                    },
                    label = { Text("Item Name") },
                    isError = itemNameError != null,
                    modifier = Modifier.fillMaxWidth()
                )
                itemNameError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                Spacer(modifier = Modifier.height(8.dp))

                // OutlinedTextField for Category input.
                OutlinedTextField(
                    value = category,
                    onValueChange = {
                        category = it
                        categoryError = null
                        Log.d("AddItemDialog", "Category changed to: $category")
                    },
                    label = { Text("Category") },
                    isError = categoryError != null,
                    modifier = Modifier.fillMaxWidth()
                )
                categoryError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                Spacer(modifier = Modifier.height(8.dp))

                // OutlinedTextField for Quantity input.
                OutlinedTextField(
                    value = quantity,
                    onValueChange = {
                        quantity = it
                        quantityError = null
                        Log.d("AddItemDialog", "Quantity changed to: $quantity")
                    },
                    label = { Text("Quantity") },
                    // Restrict keyboard to numbers only for quantity.
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = quantityError != null,
                    modifier = Modifier.fillMaxWidth()
                )
                quantityError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                Spacer(modifier = Modifier.height(8.dp))

                // OutlinedTextField for Comments input.
                OutlinedTextField(
                    value = comments,
                    onValueChange = {
                        comments = it
                        Log.d("AddItemDialog", "Comments changed to: $comments")
                    },
                    label = { Text("Comments (Optional)") }, modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    Log.d("AddItemDialog", "Add button clicked.")
                    itemNameError = null
                    categoryError = null
                    quantityError = null

                    var isValid = true

                    // Check if item name is empty.
                    if (itemName.isBlank()) {
                        itemNameError = "Item Name cannot be empty"
                        isValid = false
                    }
                    // Check if category is empty.
                    if (category.isBlank()) {
                        categoryError = "Category cannot be empty"
                        isValid = false
                    }

                    // Try to parse quantity to an integer.
                    val parsedQuantity = quantity.toIntOrNull()
                    if (parsedQuantity == null || parsedQuantity <= 0) {
                        quantityError = "Quantity must be a positive number"
                        isValid = false
                    }

                    // If all inputs are valid, create and add the item.
                    if (isValid) {
                        onAddItem(
                            PackingItem(
                                name = itemName,
                                category = category,
                                quantity = parsedQuantity
                                    ?: 0, // Use 0 if parsing failed (shouldn't happen if isValid is true)
                                comments = comments
                            )
                        )
                        onDismiss()
                    }
                }, colors = ButtonDefaults.buttonColors(Color.Green)
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    Log.d("AddItemDialog", "Cancel button clicked.")
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(Color.Red)
            ) {
                Text("Cancel")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AddItemDialogPreview() {
    AddItemDialog(onAddItem = {}, onDismiss = {})
}
