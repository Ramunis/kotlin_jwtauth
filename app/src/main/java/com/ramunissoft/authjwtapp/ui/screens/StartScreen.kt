package com.ramunissoft.authjwtapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramunissoft.authjwtapp.R
import com.ramunissoft.authjwtapp.ui.components.GradientButton
import com.ramunissoft.authjwtapp.ui.components.Loader
import com.ramunissoft.authjwtapp.ui.components.ThinText
import com.ramunissoft.authjwtapp.viewmodels.StartViewModel

@Composable
fun StartScreen(navController: NavController, viewModel: StartViewModel = viewModel()) {
    val refreshed by viewModel.refreshed.collectAsState()
    val loading by viewModel.loading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.refresh()
    }

    if(refreshed) {
        navController.navigate("products")
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if(loading) {
                Loader()
            } else {
                ThinText(stringId = R.string.welcome)
                Spacer(modifier = Modifier.height(10.dp))
                GradientButton(stringId = R.string.auth_button) {
                    navController.navigate("auth")
                }
            }
        }
    }
}