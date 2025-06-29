package com.ramunissoft.authjwtapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.ramunissoft.authjwtapp.R
import com.ramunissoft.authjwtapp.ui.theme.White

@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit
) {
    var passwordVisible by remember {
        mutableStateOf(false)
    }
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = stringResource(id = R.string.authorization_password_label),
                style = TextStyle(
                    color = White,
                    fontSize = 18.sp
                )
            )
        },
        textStyle = TextStyle(
            fontSize = 24.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Thin,
            color = White
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.White
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if(passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if(passwordVisible) R.drawable.invisible else R.drawable.visible
            Image(
                modifier = Modifier.clickable { passwordVisible = !passwordVisible },
                painter = painterResource(id = image),
                contentDescription = "Visibility"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, end = 10.dp)
    )
}