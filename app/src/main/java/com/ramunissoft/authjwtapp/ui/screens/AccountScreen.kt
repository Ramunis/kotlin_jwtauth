package com.ramunissoft.authjwtapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.ramunissoft.authjwtapp.database.User
import com.ramunissoft.authjwtapp.ui.components.Loader
import com.ramunissoft.authjwtapp.viewmodels.AccountViewModel
import com.ramunissoft.authjwtapp.R
import java.time.format.TextStyle


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AccountScreen(navController: NavController, viewModel: AccountViewModel = hiltViewModel()) {

    viewModel.getUser()
    val loading by viewModel.loading.collectAsState()
    val accountform = viewModel.user

    if(loading) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally ) {  Loader()}

    }
    else {
        Surface() {

            //Text(text = accountform?.username.toString())
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(top=50.dp)
            ) {

                //
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Back",
                        modifier = Modifier.clickable { navController.navigate("products") })

                }

                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        shape = CircleShape,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(100.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.user),
                            contentDescription = "Image",
                            modifier = Modifier
                                .wrapContentSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                }
                //
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, end = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Username", modifier = Modifier.width(100.dp))

                    //
                    TextField(
                        value =accountform?.username.toString(),
                        onValueChange = {
                        }
                    )
                    //
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, end = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Name", modifier = Modifier.width(100.dp))
                    TextField(
                        value = accountform?.firstName.toString()+" "+accountform?.lastName.toString(),
                        onValueChange = {
                        }
                    )
                }



                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, end = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Email", modifier = Modifier.width(100.dp))
                    TextField(
                        value = accountform?.email.toString(),
                        onValueChange = {
                        },
                        singleLine = false,
                        modifier = Modifier.height(150.dp)
                    )
                }
            }
           //

        }
    }
}

