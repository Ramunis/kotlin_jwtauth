package com.ramunissoft.authjwtapp.data

data class ProductItem(
    val id: Int?,
    val title: String,
    val description: String,
    val category: String,
    val price: Double?,
    val discountPercentage: Double?,
    val rating: Double?,
    val stock: Int?,
    //
    val tags: List<String>,
    val brand: String,
    val sku: String,
    //
    val dimensions: Dimension,
    //
    val warrantyInformation: String,
    val reviews: List<Review>,
    //
    val thumbnail: String
)

data class Dimension(
    val width: Double?,
    val height: Double?,
    val depth: Double?
)

data class Review(
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String
)
