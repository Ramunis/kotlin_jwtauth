package com.ramunissoft.authjwtapp.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramunissoft.authjwtapp.database.usecases.DatabaseUseCases
import com.ramunissoft.authjwtapp.keystore.TokenManager
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dbUseCases: DatabaseUseCases,@ApplicationContext private val applicationContext: Context
): ViewModel() {

    fun logout(){
        viewModelScope.launch {
            dbUseCases.deleteProducts.invoke()
        }
        viewModelScope.launch {
            dbUseCases.deleteUser.invoke()
        }

        //val context = getApplication<Application>().applicationContext
        TokenManager.saveRefreshToken(applicationContext, "")
        //
    }
}