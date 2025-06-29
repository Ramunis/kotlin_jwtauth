package com.ramunissoft.authjwtapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramunissoft.authjwtapp.R
import com.ramunissoft.authjwtapp.ui.components.GradientButton
import com.ramunissoft.authjwtapp.ui.components.ThinText
import com.ramunissoft.authjwtapp.viewmodels.AuthorizationViewModel
import com.ramunissoft.authjwtapp.viewmodels.HomeViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        ThinText(stringId = R.string.in_system)
        Spacer(modifier = Modifier.height(10.dp))
        GradientButton(stringId = R.string.products) {
            navController.navigate("products")
        }
        GradientButton(stringId = R.string.my) {
            navController.navigate("my")
        }
        GradientButton(stringId = R.string.authorization_logout) {
            viewModel.logout()
            navController.navigate("start")
        }
    }
}