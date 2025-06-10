package com.togb.paki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.togb.paki.ui.model.ListViewModel
import com.togb.paki.ui.screens.ScreenOne
import com.togb.paki.ui.screens.ScreenTwo
import com.togb.paki.ui.theme.PakiTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val listViewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PakiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Cyan
                ) {
                    Navigation(listViewModel)
                }
            }
        }
    }
}

@Composable
fun Navigation(viewModel: ListViewModel){
    val navController  = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "screen_one"
    ) {
        composable("screen_one") {
            ScreenOne(navController = navController)
        }
        composable("screen_two") {
            ScreenTwo(navController = navController, viewModel = viewModel)
        }
    }

}




