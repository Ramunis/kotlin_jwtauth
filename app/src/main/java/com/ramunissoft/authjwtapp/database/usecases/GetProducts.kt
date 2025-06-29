package com.ramunissoft.authjwtapp.database.usecases

import androidx.compose.runtime.mutableStateOf
import com.ramunissoft.authjwtapp.database.DatabaseRepository
import com.ramunissoft.authjwtapp.database.Product
import kotlinx.coroutines.flow.Flow

class GetProducts(private val databaseRepository: DatabaseRepository) {

    val mainList = mutableStateOf(emptyList<Product>())

    suspend operator fun invoke() {
        databaseRepository.getProducts().collect { list -> mainList.value = list }
    }
    //suspend operator fun invoke(): Flow<List<Product>> {
    //    return databaseRepository.getProducts()
    //}
}
