package com.ramunissoft.authjwtapp.api

import com.example.example.ExampleJson2KtKotlin
import com.ramunissoft.authjwtapp.data.LoginResponse
import com.ramunissoft.authjwtapp.data.ProductItem
import com.ramunissoft.authjwtapp.data.Products
import com.ramunissoft.authjwtapp.data.ResfeshToken
import com.ramunissoft.authjwtapp.data.User
import com.ramunissoft.authjwtapp.data.VerifedBody
import com.ramunissoft.authjwtapp.data.VerifedResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body user: User): LoginResponse

    @POST("/auth/verify")
    suspend fun verify(@Body body: VerifedBody): VerifedResponse

    @POST("auth/refresh")
    suspend fun refresh(@Body token: ResfeshToken): ResfeshToken

    @GET("products")
    suspend fun getProducts(): ExampleJson2KtKotlin

    @GET("products/{itemid}")
    suspend fun getProduct(@Path("itemid") itemid: Int?): ProductItem
}