package com.ramunissoft.authjwtapp.database

import com.ramunissoft.authjwtapp.data.Products
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DatabaseRepository(private val dao: ProductsDAO) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    val productsList: Flow<List<Product>> = dao.getProducts()
    suspend fun getProducts(): Flow<List<Product>> {
        return dao.getProducts()
    }

    fun addProduct(product: Product) {
        coroutineScope.launch(Dispatchers.IO) {
            dao.addProduct(product)
        }
    }

    fun deleteProduct() {
        coroutineScope.launch(Dispatchers.IO) {
            dao.deleteProduct()
        }
    }
    //
    suspend fun getUser(): User {
        return dao.getUser()
    }

    fun addUser(user: User) {
        coroutineScope.launch(Dispatchers.IO) {
            dao.addUser(user)
        }
    }

    fun deleteUser() {
        coroutineScope.launch(Dispatchers.IO) {
            dao.deleteUser()
        }
    }
}