package com.ramunissoft.authjwtapp.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramunissoft.authjwtapp.data.ProductItem
import com.ramunissoft.authjwtapp.data.Products
import com.ramunissoft.authjwtapp.database.Product
import com.ramunissoft.authjwtapp.database.usecases.DatabaseUseCases
import com.ramunissoft.authjwtapp.keystore.TokenManager
import com.ramunissoft.authjwtapp.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

sealed interface ItemUIState {
    data class Success(val itemSearch: ProductItem) : ItemUIState
    object Error: ItemUIState
    object Loading: ItemUIState
}

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val dbUseCases: DatabaseUseCases
): ViewModel() {

    private val repository = ProductsRepository()
    //var _product_item: Products? = null
    var itemUIState: ItemUIState by mutableStateOf(ItemUIState.Loading)
        private set


    internal fun fetchProduct(id: Int?){
        viewModelScope.launch {

            itemUIState = ItemUIState.Loading
            itemUIState = try {
                ItemUIState.Success(repository.fetchProduct(id))
            } catch (e: IOException) {
                ItemUIState.Error
            } catch (e: retrofit2.HttpException) {
                ItemUIState.Error
            }
        }
    }

    fun fetchProducts(
        id: Int?,
        title: String,
        description: String,
        category: String,
        price: Double?,
        discountPercentage: Double?,
        rating: Double?,
        stock: Int?,
        brand: String,
        humbnail: String)
    {
        viewModelScope.launch {
                dbUseCases.addProducts(Product(id,title,description,category,price,discountPercentage,rating,stock,brand,humbnail))
        }
    }

}