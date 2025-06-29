package com.ramunissoft.authjwtapp.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramunissoft.authjwtapp.data.Products
import com.ramunissoft.authjwtapp.database.Product
import com.ramunissoft.authjwtapp.database.usecases.DatabaseUseCases
import com.ramunissoft.authjwtapp.keystore.TokenManager
import com.ramunissoft.authjwtapp.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val dbUseCases: DatabaseUseCases,@ApplicationContext private val applicationContext: Context
): ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val repository = ProductsRepository()

    private val _products = MutableStateFlow<List<Products>>(emptyList())
    val products: StateFlow<List<Products>> = _products

    var mainList = mutableStateOf(emptyList<Product>())


    init {
        //fetchProducts()
        getProducts()
    }

    private fun getProducts() {
        _loading.value = true
        viewModelScope.launch {
             dbUseCases.getProducts()
        }
        mainList = dbUseCases.getProducts.mainList
        _loading.value = false

    }

    //fun delProducts(){
    //    viewModelScope.launch {
    //        dbUseCases.deleteProducts.invoke()
    //    }
    //}

    fun fetchProducts() {
        _loading.value = true
        viewModelScope.launch {
            try {
                _products.value = repository.fetchProducts()

                //dbUseCases.addProducts(Product(1,"title","decription","category",100.5,100.5,100.5,100,"brand","thumbnail"))

                for(i in products.value){
                    //println(i)
                    dbUseCases.addProducts(Product(i.id,i.title,i.description,i.category,i.price,i.discountPercentage,i.rating,i.stock,i.brand,i.thumbnail))
                }
                _loading.value = false
            } catch (e: Exception) {
                Log.e("ProductsViewModel", "${e.message}")
            }
        }
    }

    //
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

