package com.example.tradfriclient.data.util

import android.content.Context
import android.content.SharedPreferences
import com.example.tradfriclient.R

/**
 * Session manager to save and fetch data from SharedPreferences
 */
class SessionManager(context: Context) {
    companion object {
        const val USER_TOKEN = "user_token"
    }

    private var sharedPrefs: SharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    /**
     * Function to save auth token
     */
    fun saveAuthToken(token: String) {
        val editor = sharedPrefs.edit()
        editor.apply {
            putString(USER_TOKEN, "Bearer $token")
            apply()
        }
    }

    /**
     * Function to fetch auth token
     */
    fun fetchAuthToken(): String? = sharedPrefs.getString(USER_TOKEN, null)
}