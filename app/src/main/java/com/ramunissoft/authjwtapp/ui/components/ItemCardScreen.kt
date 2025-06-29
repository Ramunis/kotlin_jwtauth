package com.ramunissoft.authjwtapp.ui.components

import com.ramunissoft.authjwtapp.R
import com.ramunissoft.authjwtapp.viewmodels.ItemViewModel


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ramunissoft.authjwtapp.data.ProductItem
import com.ramunissoft.authjwtapp.data.Review


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ItemCardScreen (
    navController: NavController,
    product: ProductItem,
    itemViewModel: ItemViewModel,
    modifier: Modifier = Modifier
) {
    val mContext = LocalContext.current

    Scaffold(
        modifier = modifier,
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = {Text(product.title.toString(), fontSize = 20.sp) },
                navigationIcon = {
                    IconButton({ navController.navigate("products")}) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back",
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
        LazyColumn(
            Modifier.fillMaxSize().padding(top=100.dp, bottom = 50.dp)
        ) {


            item {
                Card(
                    modifier = modifier.padding(4.dp).fillMaxWidth().requiredHeight(296.dp)
                ) {
                    AsyncImage(
                        modifier = modifier.fillMaxSize(),
                        model = ImageRequest.Builder(context = LocalContext.current).data(product.thumbnail)
                            .crossfade(true).build(),
                        error = painterResource(id = R.drawable.ic_broken_image),
                        placeholder = painterResource(id = R.drawable.ic_book_96),
                        contentDescription = stringResource(id = R.string.app_name),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            //
            item {
                Row {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.stars),
                            contentDescription = "Local Image",
                            modifier = Modifier.padding(start = 30.dp).size(50.dp)
                        )
                        Text(
                            text = "Rating " + product.rating.toString() + " /5",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = modifier.padding(start = 10.dp)
                        )
                    }
                    Spacer(modifier = Modifier.size(30.dp))

                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.money),
                            contentDescription = "Local Image",
                            modifier = Modifier.padding(start = 30.dp).size(50.dp)
                        )
                        Text(
                            text = "Price: " + product.price.toString() + " $",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))

                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.perc),
                            contentDescription = "Local Image",
                            modifier = Modifier.padding(start = 30.dp).size(50.dp)
                        )
                        Text(
                            text = "Discount: " + product.discountPercentage+" %",
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                }
            }
            //
            item {
                Column(

                ) {


                    Text(
                        text = "Description: " + product.description.toString(),
                        textAlign = TextAlign.Start,
                        fontSize = 23.sp,
                        color = Color.Black,
                        modifier = Modifier.background(
                            Color(0xffdddddd)
                        ).padding(8.dp).fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.size(5.dp))



                    Text(
                        text = "Brand: " + product.brand.toString(),
                        textAlign = TextAlign.Start,
                        fontSize = 23.sp,
                        color = Color.Black,
                        modifier = Modifier.background(
                            Color(0xffdddddd)
                        ).padding(8.dp).fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.size(5.dp))



                    Text(
                        text = "Warranty: " + product.warrantyInformation.toString(),
                        textAlign = TextAlign.Start,
                        fontSize = 23.sp,
                        color = Color.Black,
                        modifier = Modifier.background(
                            Color(0xffdddddd)
                        ).padding(8.dp).fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.size(5.dp))



                    Text(
                            text = "Stock: " + product.stock.toString(),
                    textAlign = TextAlign.Start,
                    fontSize = 23.sp,
                    color = Color.Black,
                    modifier = Modifier.background(
                        Color(0xffdddddd)
                    ).padding(8.dp).fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.size(5.dp))

                    Text(
                        text = "SKU: " + product.sku.toString(),
                        textAlign = TextAlign.Start,
                        fontSize = 23.sp,
                        color = Color.Black,
                        modifier = Modifier.background(
                            Color(0xffdddddd)
                        ).padding(8.dp).fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                }

            }
            //
            item {
                Column(

                ) {


                    Text(
                        text = "Demensions:",
                        textAlign = TextAlign.Start,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.background(
                            Color(0xffdddddd)
                        ).padding(8.dp).fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.size(5.dp))



                    Text(
                        text = "width: " + product.dimensions.width.toString(),
                        textAlign = TextAlign.Start,
                        fontSize = 23.sp,
                        color = Color.Black,
                        modifier = Modifier.background(
                            Color(0xffdddddd)
                        ).padding(8.dp).fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.size(5.dp))



                    Text(
                        text = "height: " +product.dimensions.height.toString(),
                        textAlign = TextAlign.Start,
                        fontSize = 23.sp,
                        color = Color.Black,
                        modifier = Modifier.background(
                            Color(0xffdddddd)
                        ).padding(8.dp).fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.size(5.dp))



                    Text(
                        text = "depth: " + product.dimensions.depth.toString(),
                        textAlign = TextAlign.Start,
                        fontSize = 23.sp,
                        color = Color.Black,
                        modifier = Modifier.background(
                            Color(0xffdddddd)
                        ).padding(8.dp).fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                }

            }
            //
            item {
                Text(
                    text = "Tags",
                    fontSize = 28.sp,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = modifier.padding(start = 10.dp, top = 10.dp)
                )
            }

            item {
                Column(
                ) {
                    for (tag in product.tags) {
                        Text(
                            text = tag,
                            textAlign = TextAlign.Start,
                            fontSize = 23.sp,
                            color = Color.Black,
                            modifier = Modifier.background(
                                Color(0xffdddddd)
                            ).padding(8.dp).fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.size(5.dp))
                    }

                }
            }
            //
            item {
                Text(
                    text = "Reviews",
                    fontSize = 28.sp,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = modifier.padding(start = 10.dp, top = 10.dp)
                )
            }

            item {
                Column(
                ) {
                    for (rew in product.reviews) {
                        Text(
                            text = rew.reviewerName.toString(),
                            textAlign = TextAlign.Start,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.background(
                                Color(0xffdddddd)
                            ).padding(8.dp).fillMaxWidth()
                        )
                        Text(
                            text = rew.comment.toString(),
                            textAlign = TextAlign.Start,
                            fontSize = 23.sp,
                            color = Color.Black,
                            modifier = Modifier.background(
                                Color(0xffdddddd)
                            ).padding(8.dp).fillMaxWidth()
                        )
                        Text(
                            text = rew.rating.toString()+" /5 ☆",
                            textAlign = TextAlign.End,
                            fontSize = 23.sp,
                            color = Color.Black,
                            modifier = Modifier.background(
                                Color(0xffdddddd)
                            ).padding(8.dp).fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.size(5.dp))
                    }

                }
            }
            //
//            item {
//                Button(onClick = {itemViewModel.fetchProducts()
//                    Toast.makeText(mContext, "Added to DB", Toast.LENGTH_LONG)
//                        .show()}){
//                    Text("Click", fontSize = 25.sp)
//                }
//            }





        }
        //
    }

}










