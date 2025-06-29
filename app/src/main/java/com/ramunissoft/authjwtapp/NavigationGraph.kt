package com.ramunissoft.authjwtapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ramunissoft.authjwtapp.ui.screens.AccountScreen
import com.ramunissoft.authjwtapp.ui.screens.AuthorizationScreen
import com.ramunissoft.authjwtapp.ui.screens.HomeScreen
import com.ramunissoft.authjwtapp.ui.screens.ProductsItemScreen
import com.ramunissoft.authjwtapp.ui.screens.ProductsScreen
import com.ramunissoft.authjwtapp.ui.screens.StartScreen


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "start") {
        composable("start") { StartScreen(navController = navController)}
        composable("auth") { AuthorizationScreen(navController = navController)}
        composable("home") { HomeScreen(navController = navController) }
        composable("my") { AccountScreen(navController = navController) }
        composable("products") { ProductsScreen(navController = navController) }
        composable("itemPage" + "/{id}")
        {
            val ids = it.arguments?.getString("id")
            ProductsItemScreen(navController,ids)
        }
    }
}