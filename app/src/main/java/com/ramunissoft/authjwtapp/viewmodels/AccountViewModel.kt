package com.ramunissoft.authjwtapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramunissoft.authjwtapp.database.User
import com.ramunissoft.authjwtapp.database.usecases.DatabaseUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class AccountViewModel @Inject constructor(
    private val dbUseCases: DatabaseUseCases
): ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    var user: User? = null

    fun getUser()
    {
        _loading.value = true
        viewModelScope.launch {
        user = dbUseCases.getUser()
            _loading.value = false
    }
    }
}
