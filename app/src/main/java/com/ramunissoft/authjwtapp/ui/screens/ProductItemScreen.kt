package com.ramunissoft.authjwtapp.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ramunissoft.authjwtapp.ui.components.ItemScreen
import com.ramunissoft.authjwtapp.viewmodels.ItemViewModel
import com.ramunissoft.authjwtapp.viewmodels.ProductsViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsItemScreen(
    navController: NavController,
    ids: String?,
    modifier: Modifier = Modifier, itemViewModel: ItemViewModel = hiltViewModel()
) {
    itemViewModel.fetchProduct(ids?.toInt())

    Scaffold(
        modifier = modifier,

        ) {
        Surface(modifier = modifier,
            color = Color.White
        ) {
            ItemScreen(
                navController = navController,
                ids = ids,
                itemUIState = itemViewModel.itemUIState,
                itemViewModel = itemViewModel
            )

            }

        }
    }




