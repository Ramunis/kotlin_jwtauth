package com.ramunissoft.authjwtapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product (
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val title: String,
    val description: String,
    val category: String,
    val price: Double?,
    val discountPercentage: Double?,
    val rating: Double?,
    val stock: Int?,
    val brand: String,
    val thumbnail: String
)

