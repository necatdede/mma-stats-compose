package com.necatdede.mmastatscompose.data.model

data class RankingCategory(
    val id: String,
    val categoryName: String,
    val champion: FighterSummary,
    val fighters: List<FighterSummary>
)
