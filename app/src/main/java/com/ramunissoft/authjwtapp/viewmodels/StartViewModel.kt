package com.ramunissoft.authjwtapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.ramunissoft.authjwtapp.api.RetrofitInstance
import com.ramunissoft.authjwtapp.data.ResfeshToken
import com.ramunissoft.authjwtapp.keystore.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StartViewModel(application: Application): AndroidViewModel(application) {
    private val _refreshed = MutableStateFlow(false)
    val refreshed: StateFlow<Boolean> = _refreshed

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun refresh() {
        _loading.value = true
        viewModelScope.launch {
            try {
                val context = getApplication<Application>().applicationContext
                val storedToken = TokenManager.getRefreshToken(context)
                if (storedToken !=null && storedToken!=""){
                    val response = RetrofitInstance.api.refresh(ResfeshToken(storedToken))
                    if (response.refreshToken != ""){
                        TokenManager.saveRefreshToken(context,response.refreshToken)
                        _refreshed.value = true
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _loading.value = false
            }
        }

    }
}