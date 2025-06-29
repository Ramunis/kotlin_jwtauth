package com.ramunissoft.authjwtapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramunissoft.authjwtapp.AuthSteps
import com.ramunissoft.authjwtapp.R
import com.ramunissoft.authjwtapp.ui.components.GradientButton
import com.ramunissoft.authjwtapp.ui.components.Loader
import com.ramunissoft.authjwtapp.ui.components.MyTextField
import com.ramunissoft.authjwtapp.ui.components.PasswordField
import com.ramunissoft.authjwtapp.ui.components.ThinText
import com.ramunissoft.authjwtapp.ui.theme.Dark
import com.ramunissoft.authjwtapp.ui.theme.White
import com.ramunissoft.authjwtapp.viewmodels.AuthorizationViewModel


@Composable
fun AuthorizationScreen(navController: NavController, viewModel: AuthorizationViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val loading by viewModel.loading.collectAsState()
    val toastMessage by viewModel.toastMessage.collectAsState()
    val toastServerMessage by viewModel.toastServerMessage.collectAsState()
    val step by viewModel.step.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val passwordRepeated by viewModel.passwordRepeated.collectAsState()
    val code by viewModel.code.collectAsState()

    LaunchedEffect(toastMessage, toastServerMessage) {
        toastMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        toastServerMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .align(Alignment.Center)
                .clip(RoundedCornerShape(16.dp))
                .background(Dark)
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ThinText(stringId = R.string.authorization_title, color = White)
            when(step) {
                AuthSteps.LOGIN -> {
                    MyTextField(value = email, labelStringId = R.string.authorization_email_label, type = KeyboardType.Email) {
                            newValue -> viewModel.onEmailFieldValueChanged(newValue)
                    }
                    PasswordField(value = password) {
                            newValue -> viewModel.onPasswordFieldValueChanged(newValue)
                    }
                    GradientButton(stringId = R.string.next) {
                        keyboardController?.hide()
                        viewModel.onLoginButtonClick()
                    }
                }

                AuthSteps.PASSWORD_REPEAT -> {
                    ThinText(
                        stringId = R.string.authorization_repeat_password,
                        size = 20,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    PasswordField(value = passwordRepeated) {
                            newValue -> viewModel.onPasswordRepeatedFieldValueChanged(newValue)
                    }
                    GradientButton(stringId = R.string.next) {
                        keyboardController?.hide()
                        viewModel.checkPasswordsMatch()
                    }
                }

                AuthSteps.CODE -> {
                    ThinText(
                        stringId = R.string.authorization_code_message,
                        size = 20,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    MyTextField(value = code, labelStringId = R.string.authorization_code_label, type = KeyboardType.Number) {
                            newValue -> viewModel.onCodeFieldValueChanged(newValue)
                    }
                }

                AuthSteps.FINAL -> navController.navigate("products")
            }
            if(loading) {
                Loader()
            }
        }
    }
}