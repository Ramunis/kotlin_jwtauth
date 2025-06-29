package com.ramunissoft.authjwtapp.database.usecases

import androidx.compose.runtime.mutableStateOf
import com.ramunissoft.authjwtapp.database.DatabaseRepository
import com.ramunissoft.authjwtapp.database.Product
import com.ramunissoft.authjwtapp.database.User
import kotlinx.coroutines.flow.Flow

class AddUser(private val databaseRepository: DatabaseRepository) {

    suspend operator fun invoke(user: User) {
        databaseRepository.addUser(user)
    }
}
