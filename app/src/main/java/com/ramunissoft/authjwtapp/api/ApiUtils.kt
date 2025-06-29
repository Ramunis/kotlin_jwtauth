package com.ramunissoft.authjwtapp.api

import org.json.JSONObject
import retrofit2.HttpException

class ApiUtils {
    companion object {
        private const val ON_EXCEPTION_ERROR_TEXT = "Unknown server error"

        fun handleHttpException(e: HttpException, cb: (String)->Unit){
            val errorBody = e.response()?.errorBody()?.string()
            val errorMessage = try {
                JSONObject(errorBody!!).getString("message")
            } catch (ex: Exception) {
                ON_EXCEPTION_ERROR_TEXT
            }
            cb(errorMessage)
        }
    }
}