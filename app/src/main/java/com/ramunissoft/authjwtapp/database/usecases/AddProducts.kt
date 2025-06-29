package com.ramunissoft.authjwtapp.database.usecases

import com.ramunissoft.authjwtapp.data.Products
import com.ramunissoft.authjwtapp.database.DatabaseRepository
import com.ramunissoft.authjwtapp.database.Product
import com.ramunissoft.authjwtapp.database.ProductsDAO

class AddProducts(private val databaseRepository: DatabaseRepository) {

    suspend operator fun invoke(product: Product) {
        databaseRepository.addProduct(product)
    }
}
