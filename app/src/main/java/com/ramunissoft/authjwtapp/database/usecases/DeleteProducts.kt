package com.ramunissoft.authjwtapp.database.usecases

import androidx.compose.runtime.mutableStateOf
import com.ramunissoft.authjwtapp.database.DatabaseRepository
import com.ramunissoft.authjwtapp.database.Product
import kotlinx.coroutines.flow.Flow

class DeleteProducts(private val databaseRepository: DatabaseRepository) {

    suspend operator fun invoke() {
        databaseRepository.deleteProduct()
    }
}
