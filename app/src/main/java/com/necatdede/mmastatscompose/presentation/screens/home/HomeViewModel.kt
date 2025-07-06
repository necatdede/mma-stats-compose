package com.necatdede.mmastatscompose.presentation.screens.home


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.necatdede.mmastatscompose.data.model.Fighter
import com.necatdede.mmastatscompose.data.repository.FighterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: FighterRepository
) : ViewModel() {

    var fighters by mutableStateOf<Map<String, Fighter>>(emptyMap())
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    init {
        fetchFighters()
    }

    private fun fetchFighters() {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {
                fighters = repository.getFighters()
            } catch (e: Exception) {
                error = e.message
            }
            isLoading = false
        }
    }
}
