package com.ramunissoft.authjwtapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class, Product::class, Setting::class], version = 1)
abstract class DatabaseInstance: RoomDatabase() {
    abstract val dao: ProductsDAO
}