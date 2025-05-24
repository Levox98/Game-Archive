package com.levox.game_archive.data

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext

@Singleton
class AppCache @Inject constructor(
    @ApplicationContext context: Context
) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)

    var authToken: String? = null
        get() = sharedPreferences.getString(AUTH_TOKEN_KEY, null)
        set(value) {
            sharedPreferences.edit { putString(AUTH_TOKEN_KEY, value) }
            field = value
        }

    companion object {
        const val AUTH_TOKEN_KEY = "AUTH_TOKEN"
    }
}