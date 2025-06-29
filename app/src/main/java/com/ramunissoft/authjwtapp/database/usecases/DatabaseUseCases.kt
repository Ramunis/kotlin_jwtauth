package com.ramunissoft.authjwtapp.database.usecases

data class DatabaseUseCases(
    val getProducts: GetProducts,
    val deleteProducts: DeleteProducts,
    val addProducts: AddProducts,
    val getUser: GetUser,
    val addUser: AddUser,
    val deleteUser: DeleteUser)