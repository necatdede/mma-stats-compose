package com.necatdede.mmastatscompose.presentation.screens.settings

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.necatdede.mmastatscompose.utils.LocaleManager
import com.necatdede.mmastatscompose.R

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {
    val state by viewModel.settingsState.collectAsState()

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(stringResource(R.string.settings), style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        Text(stringResource(R.string.select_language), style = MaterialTheme.typography.titleMedium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = state.language == LanguageOption.TURKISH,
                onClick = { viewModel.setLanguage(LanguageOption.TURKISH)
                    updateLanguage(context, LanguageOption.TURKISH.code)
                    println(state.language.code)
                }
            )
            Text(stringResource(R.string.turkish))
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = state.language == LanguageOption.ENGLISH,
                onClick = { viewModel.setLanguage(LanguageOption.ENGLISH)
                    updateLanguage(context, LanguageOption.ENGLISH.code)
                    println(state.language.code)
                   }
            )
            Text(stringResource(R.string.english))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(stringResource(R.string.weight_unit), style = MaterialTheme.typography.titleMedium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = state.weightUnit == WeightUnit.KG,
                onClick = { viewModel.setWeightUnit(WeightUnit.KG) }
            )
            Text("kg")
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = state.weightUnit == WeightUnit.LBS,
                onClick = { viewModel.setWeightUnit(WeightUnit.LBS) }
            )
            Text("lbs")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(stringResource(R.string.height_unit), style = MaterialTheme.typography.titleMedium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = state.heightUnit == HeightUnit.CM,
                onClick = { viewModel.setHeightUnit(HeightUnit.CM) }
            )
            Text("cm")
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = state.heightUnit == HeightUnit.INCHES,
                onClick = { viewModel.setHeightUnit(HeightUnit.INCHES) }
            )
            Text("inches")
        }
    }
}

private fun updateLanguage(context: Context, language: String) {
    LocaleManager.saveLanguage(context,language)
    LocaleManager.setLocale(context, language)
    (context as? Activity)?.recreate()  // Aktiviteyi yeniden başlat
}
