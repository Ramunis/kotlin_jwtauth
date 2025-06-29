package com.ramunissoft.authjwtapp.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Database
import com.ramunissoft.authjwtapp.AuthSteps
import com.ramunissoft.authjwtapp.R
import com.ramunissoft.authjwtapp.api.ApiUtils
import com.ramunissoft.authjwtapp.api.RetrofitInstance
import com.ramunissoft.authjwtapp.data.LoginResponse
import com.ramunissoft.authjwtapp.data.User
import com.ramunissoft.authjwtapp.data.VerifedBody
import com.ramunissoft.authjwtapp.database.DatabaseInstance
import com.ramunissoft.authjwtapp.database.DatabaseRepository
import com.ramunissoft.authjwtapp.database.usecases.DatabaseUseCases
import com.ramunissoft.authjwtapp.keystore.TokenManager
import com.ramunissoft.authjwtapp.repository.ProductsRepository
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val dbUseCases: DatabaseUseCases,@ApplicationContext private val applicationContext: Context
): ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _toastServerMessage = MutableStateFlow<String?>(null)
    val toastServerMessage: StateFlow<String?> = _toastServerMessage

    private val _toastMessage = MutableStateFlow<Int?>(null)
    val toastMessage: StateFlow<Int?> = _toastMessage

    private val _step = MutableStateFlow(AuthSteps.LOGIN)
    val step: StateFlow<AuthSteps> = _step

    private val _email = MutableStateFlow("emilys")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("emilyspass")
    val password: StateFlow<String> = _password

    private val _passwordRepeated = MutableStateFlow("")
    val passwordRepeated: StateFlow<String> = _passwordRepeated

    private val _refreshToken = MutableStateFlow<String?>(null)
    private val refreshToken: StateFlow<String?> = _refreshToken

    private val _code = MutableStateFlow("")
    val code: StateFlow<String> = _code


    fun onEmailFieldValueChanged(newValue: String) {
        _email.value = newValue
    }

    fun onPasswordFieldValueChanged(newValue: String) {
        _password.value = newValue
    }

    fun onPasswordRepeatedFieldValueChanged(newValue: String) {
        _passwordRepeated.value = newValue
    }

    fun onCodeFieldValueChanged(newValue: String) {
        _code.value = newValue
        if(_code.value.length == 6) {
            verify()
        }
    }

    fun onLoginButtonClick() {
        if(email.value.isBlank()) {
            setToastMessage(R.string.authorization_email_error)
            return
        }
        if(password.value.isBlank()) {
            setToastMessage(R.string.authorization_password_error)
            return
        }
        login()
    }

    fun checkPasswordsMatch() {
        if(password.value == passwordRepeated.value) {
            _step.value = AuthSteps.CODE
        } else {
            setToastMessage(R.string.authorization_passwords_match_error)
        }
    }

    private suspend fun storeUser(
        id: Int,
        username: String,
        email: String,
        firstName: String,
        lastName: String,
        gender: String,
        image: String
    ){

        dbUseCases.addUser(com.ramunissoft.authjwtapp.database.User(id, username, email, firstName, lastName, gender, image))

    }

    private fun login() {
        _loading.value = true
        viewModelScope.launch {
            try {
                val loginResponse = RetrofitInstance.api.login(User(email.value, password.value))
                if(loginResponse.refreshToken!=null && loginResponse.refreshToken!="") {//
                    //val context = getApplication<Application>().applicationContext
                    //println("HTTP Response:"+loginResponse.refreshToken+loginResponse.id+" "+loginResponse.username+" "+loginResponse.lastName)
                    storeUser(1, loginResponse.username, loginResponse.email, loginResponse.firstName, loginResponse.lastName, loginResponse.gender, loginResponse.image)
                    TokenManager.saveRefreshToken(applicationContext, loginResponse.refreshToken)

                    _step.value = AuthSteps.FINAL
                    println("Success Signin")
                } else {
                    _refreshToken.value = loginResponse.refreshToken
                    _step.value = AuthSteps.LOGIN//
                    println("Error Signin")

                }
            } catch (e: HttpException) {
                ApiUtils.handleHttpException(e) { error ->
                    setToastServerMessage(error)
                }
            }
            catch (e: Exception) {
                setToastMessage(R.string.network_error)
            }
            finally {
                _loading.value = false
            }
        }
    }

    private fun verify() {
        _loading.value = true
        viewModelScope.launch {
            try {
                val verifyResponse = RetrofitInstance.api.verify(VerifedBody(email.value, code.value))
                if(verifyResponse.isVerifed) {
                    if(refreshToken.value != null) {
                        //val context = getApplication<Application>().applicationContext
                        TokenManager.saveRefreshToken(applicationContext, refreshToken.value!!)
                    }
                    _step.value = AuthSteps.FINAL
                }
            } catch (e: HttpException) {
                ApiUtils.handleHttpException(e) { error ->
                    setToastServerMessage(error)
                }
            } catch (e: Exception) {
                Log.e("TAG", e.message!!)
                setToastMessage(R.string.network_error)
            } finally {
                _loading.value = false
            }
        }
    }

    private fun setToastMessage(message: Int) {
        _toastMessage.value = null
        _toastServerMessage.value = null
        _toastMessage.value = message
    }

    private fun setToastServerMessage(message: String) {
        _toastMessage.value = null
        _toastServerMessage.value = null
        _toastServerMessage.value = message
    }

}