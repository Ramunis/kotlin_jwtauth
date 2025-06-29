package com.ramunissoft.authjwtapp.ui.components

import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ramunissoft.authjwtapp.R
import com.ramunissoft.authjwtapp.viewmodels.ItemUIState
import com.ramunissoft.authjwtapp.viewmodels.ItemViewModel


@Composable
fun ItemScreen (
    navController: NavController,
    ids: String?,
    itemUIState: ItemUIState,
    itemViewModel: ItemViewModel,
    modifier: Modifier = Modifier
) {

    when (itemUIState) {
        is ItemUIState.Loading ->  Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally ) {  Loader()}
        is ItemUIState.Success -> ItemCardScreen(
            navController = navController,
            product = itemUIState.itemSearch,
            itemViewModel = itemViewModel,
            modifier = modifier
        )
        is ItemUIState.Error -> Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally ) {
            Image(
            painter = painterResource(id = R.drawable.conn),
            contentDescription = "Image",
            modifier = Modifier
                .wrapContentSize(),
            contentScale = ContentScale.Crop
        )

            TextButton({navController.navigate("products")}){
                Text("No connection", fontSize = 28.sp, color = Color.Blue)
            }


        }

    }
}

