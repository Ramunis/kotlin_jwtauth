package com.ramunissoft.authjwtapp.data

data class LoginResponse(
    val refreshToken: String,
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String)