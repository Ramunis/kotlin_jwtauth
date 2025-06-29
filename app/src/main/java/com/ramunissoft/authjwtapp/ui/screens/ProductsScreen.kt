package com.ramunissoft.authjwtapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ramunissoft.authjwtapp.R
import com.ramunissoft.authjwtapp.api.RetrofitInstance
import com.ramunissoft.authjwtapp.data.ProductItem
import com.ramunissoft.authjwtapp.data.Products
import com.ramunissoft.authjwtapp.database.Product
import com.ramunissoft.authjwtapp.repository.ProductsRepository
import com.ramunissoft.authjwtapp.ui.components.Loader
import com.ramunissoft.authjwtapp.viewmodels.AccountViewModel
import com.ramunissoft.authjwtapp.viewmodels.HomeViewModel
import com.ramunissoft.authjwtapp.viewmodels.ProductsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductsScreen (navController: NavController,viewModel: ProductsViewModel = hiltViewModel()) {
    //val products by viewModel.productsdb.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val mainList = viewModel.mainList.value


    Scaffold(
        modifier = Modifier,
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = { Text(text = "Локальное хранилище", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton({ viewModel.logout()
                        navController.navigate("auth")}) {
                        Icon(
                            Icons.Filled.ExitToApp,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                actions = {


                    IconButton({
                        viewModel.fetchProducts()

                    }) {
                        Icon(
                            Icons.Outlined.Refresh,
                            contentDescription = "Update",
                            tint = Color.White
                        )
                    }
                    IconButton({
                        navController.navigate("my")

                    }) {
                        Icon(
                            Icons.Outlined.AccountBox,
                            contentDescription = "My",
                            tint = Color.White
                        )
                    }



                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.blue),
                    titleContentColor = Color.White,
                    navigationIconContentColor = colorResource(id = R.color.blue),
                    actionIconContentColor = colorResource(id = R.color.blue)
                )
            )
        }, bottomBar = {}

    )
    {
        if (loading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) { Loader() }

        } else {
            LazyColumn(modifier = Modifier, contentPadding = PaddingValues(top = 120.dp))
            {
                itemsIndexed(mainList) { _, item ->
                    ProductItemScreen(navController = navController, product = item)
                }


            }

        }


    }


}

    @Composable
    fun ProductItemScreen(navController: NavController, product: Product) {
        Card(Modifier.padding(8.dp), elevation = CardDefaults.cardElevation(4.dp)) {
            Row(
                modifier = Modifier.fillMaxSize().padding(10.dp)
                    .clickable(onClick = { navController.navigate("itemPage" + "/${product.id}") })
            ) {
                AsyncImage(
                    model = product.thumbnail,
                    contentDescription = product.title,
                    modifier = Modifier.size(80.dp)
                )
                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Text(text = product.title, fontWeight = FontWeight.Bold)
                    Text(text = "Price: ${product.price} $")
                    Text(text = "Rating: ${product.rating} /5")
                }
            }
        }
    }
