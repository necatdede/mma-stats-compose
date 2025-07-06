package com.necatdede.mmastatscompose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.necatdede.mmastatscompose.data.model.Fighter
import com.necatdede.mmastatscompose.R


@Composable
fun FighterCard(fighter: Fighter) {
    Box(
        modifier = Modifier
            .padding(12.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF1E1E1E))
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Image(
                painter = rememberAsyncImagePainter(fighter.imgUrl),
                contentDescription = fighter.name,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "${fighter.name ?: "Bilinmeyen"} ${if (!fighter.nickname.isNullOrBlank()) "\"${fighter.nickname}\"" else ""}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Text(
                text = fighter.category ?: "Bilinmeyen Kategori",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Kayıt: ${fighter.wins}-${fighter.losses}-${fighter.draws ?: "0"}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoItem(title = stringResource(R.string.country), value = fighter.placeOfBirth ?: "-")
                InfoItem(title = stringResource(R.string.age), value = fighter.age ?: "-")
                InfoItem(title = stringResource(R.string.height), value = "${fighter.height}\"")
                InfoItem(title = stringResource(R.string.weight), value = "${fighter.weight} lbs")
            }
        }
    }
}

@Composable
fun InfoItem(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = title, style = MaterialTheme.typography.labelSmall, color = Color.LightGray)
        Text(text = value, style = MaterialTheme.typography.bodySmall, color = Color.White, fontWeight = FontWeight.SemiBold)
    }
}
