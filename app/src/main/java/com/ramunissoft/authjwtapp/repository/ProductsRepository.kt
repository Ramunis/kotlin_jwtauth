package com.ramunissoft.authjwtapp.repository

import com.ramunissoft.authjwtapp.api.RetrofitInstance
import com.ramunissoft.authjwtapp.data.ProductItem
import com.ramunissoft.authjwtapp.data.Products

class ProductsRepository {
    suspend fun fetchProducts(
    ): List<Products> = RetrofitInstance.api.getProducts().products.map {items ->
        Products(
            id = items?.id,
            title = items?.title.toString(),
            description = items?.description.toString(),
            category = items?.category.toString(),
            price = items?.price,
            discountPercentage = items?.discountPercentage,
            rating = items?.rating,
            stock = items?.stock,
            brand = items?.brand.toString(),
            thumbnail = items?.thumbnail.toString()
        )
    }

    suspend fun fetchProduct(itemid: Int?): ProductItem = RetrofitInstance.api.getProduct(itemid)

}