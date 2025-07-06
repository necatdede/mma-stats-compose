package com.necatdede.mmastatscompose.data.remote


import com.necatdede.mmastatscompose.data.model.Fighter
import com.necatdede.mmastatscompose.data.model.RankingCategory
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path

interface OctagonApiService {
    @GET("rankings")
    suspend fun getRankings(): Response<RankingCategory>

    @GET("fighters")
    suspend fun getFighters(): Response<Map<String, Fighter>>

    @GET("fighter/{id}")
    suspend fun getFighter(@Path("id") id: String): Response<Fighter>
}
