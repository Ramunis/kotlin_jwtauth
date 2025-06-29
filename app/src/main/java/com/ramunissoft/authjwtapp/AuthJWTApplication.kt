package com.ramunissoft.authjwtapp


import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AuthJWTApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }


}