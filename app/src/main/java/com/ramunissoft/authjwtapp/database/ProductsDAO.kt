package com.ramunissoft.authjwtapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductsDAO {
    @Query("SELECT * FROM products")
    fun getProducts(): Flow<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: Product)

    @Query("DELETE FROM products")
    suspend fun deleteProduct()

    @Query("SELECT * FROM users WHERE id = 1")
    suspend fun getUser(): com.ramunissoft.authjwtapp.database.User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: com.ramunissoft.authjwtapp.database.User)

    @Query("DELETE FROM users WHERE id = 1")
    fun deleteUser()

}