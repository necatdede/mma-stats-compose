package com.necatdede.mmastatscompose.utils

import android.content.Context
import android.content.res.Configuration
import java.util.Locale
import androidx.core.content.edit

object LocaleManager {

    private const val PREFS_NAME = "app_prefs"
    private const val KEY_LANGUAGE = "app_language"

    fun saveLanguage(context: Context, language: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit { putString(KEY_LANGUAGE, language) }
    }

    fun getSavedLanguage(context: Context): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_LANGUAGE, "en") ?: "en"
    }


    fun setLocale(context: Context, language: String): Context {

        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        config.setLocales(android.os.LocaleList(locale))
        return context.createConfigurationContext(config)
    }
}