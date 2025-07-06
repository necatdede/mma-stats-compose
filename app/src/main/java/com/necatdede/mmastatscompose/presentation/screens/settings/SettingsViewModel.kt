package com.necatdede.mmastatscompose.presentation.screens.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import com.necatdede.mmastatscompose.utils.LocaleManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

enum class LanguageOption(val code:String) { TURKISH("tr"), ENGLISH("en") }
enum class WeightUnit { KG, LBS }
enum class HeightUnit { CM, INCHES }

data class SettingsState(
    val language: LanguageOption = LanguageOption.ENGLISH,
    val weightUnit: WeightUnit = WeightUnit.KG,
    val heightUnit: HeightUnit = HeightUnit.CM
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    @ApplicationContext context: Context
)  : ViewModel() {

    private val initialLanguage = when (LocaleManager.getSavedLanguage(context)) {
        "tr" -> LanguageOption.TURKISH
        else -> LanguageOption.ENGLISH
    }

    private val _settingsState = MutableStateFlow(SettingsState(language = initialLanguage))
    val settingsState: StateFlow<SettingsState> = _settingsState

    fun setLanguage(language: LanguageOption) {
        _settingsState.value = _settingsState.value.copy(language = language)
    }

    fun setWeightUnit(unit: WeightUnit) {
        _settingsState.value = _settingsState.value.copy(weightUnit = unit)
    }

    fun setHeightUnit(unit: HeightUnit) {
        _settingsState.value = _settingsState.value.copy(heightUnit = unit)

    }
}