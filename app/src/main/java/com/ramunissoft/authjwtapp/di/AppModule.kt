package com.ramunissoft.authjwtapp.di

import android.app.Application
import androidx.room.Room
import com.ramunissoft.authjwtapp.database.DatabaseInstance
import com.ramunissoft.authjwtapp.database.DatabaseRepository
import com.ramunissoft.authjwtapp.database.usecases.AddProducts
import com.ramunissoft.authjwtapp.database.usecases.AddUser
import com.ramunissoft.authjwtapp.database.usecases.DatabaseUseCases
import com.ramunissoft.authjwtapp.database.usecases.DeleteProducts
import com.ramunissoft.authjwtapp.database.usecases.DeleteUser
import com.ramunissoft.authjwtapp.database.usecases.GetProducts
import com.ramunissoft.authjwtapp.database.usecases.GetUser

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMainDb(app: Application): DatabaseInstance {
        return Room.databaseBuilder(
            app,
            DatabaseInstance::class.java,
            "favorites_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(db: DatabaseInstance): DatabaseRepository {
        return DatabaseRepository(db.dao)
    }


    @Provides
    @Singleton
    fun provideFavoriteUseCases(repository: DatabaseRepository): DatabaseUseCases {
        return DatabaseUseCases(
            getProducts = GetProducts(repository),
            deleteProducts = DeleteProducts(repository),
            addProducts = AddProducts(repository),
            getUser = GetUser(repository),
            addUser = AddUser(repository),
            deleteUser = DeleteUser(repository)
        )

    }
}