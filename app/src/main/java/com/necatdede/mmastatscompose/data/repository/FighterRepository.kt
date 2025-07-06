package com.necatdede.mmastatscompose.data.repository

import com.necatdede.mmastatscompose.data.model.Fighter
import com.necatdede.mmastatscompose.data.remote.OctagonApiService
import javax.inject.Inject

class FighterRepository @Inject constructor(
    private val api: OctagonApiService
) {
    suspend fun getFighters(): Map<String,Fighter> {
        val response = api.getFighters()
        if (response.isSuccessful) {

            return response.body() ?: emptyMap()
        } else {
            throw Exception("API Error: ${response.code()}")
        }
    }
}