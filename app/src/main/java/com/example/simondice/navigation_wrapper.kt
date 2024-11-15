package com.example.simondice

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun NavigationWrapper(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "home") {
        composable("home") {
            Home({ navHostController.navigate("creditos") },
                { navHostController.navigate("settings") },
                { navHostController.navigate("juego") })
        }
        composable("settings") {
            Settings { navHostController.navigate("home") }
        }
        composable("juego") {
            GameBoardScreen{ navHostController.navigate("home") }
        }
        composable("creditos") {
            Credits{ navHostController.navigate("home") }
        }

    }
}