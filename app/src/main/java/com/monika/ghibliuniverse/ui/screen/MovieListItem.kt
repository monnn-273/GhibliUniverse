package com.monika.ghibliuniverse.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.monika.ghibliuniverse.ui.theme.GhibliUniverseTheme

@Composable
fun MovieListItem(
    title: String,
    posterUrl: String,
    caption: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(8.dp)
    ) {
        AsyncImage(
            model = posterUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(80.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 16.dp)
        )
        {
            Text(
                text = title,
                fontWeight = FontWeight.ExtraBold,
            )
            Text(
                text = caption,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListItemPreview() {
    GhibliUniverseTheme {
        MovieListItem(
            title = "Howl's Moving Castle",
            caption = "Terbanglah bersama dalam petualangan magis di Castle Howl yang Bergerak! Rasakan keajaiban dan kecantikan dunia Miyazaki dalam 'Howl's Moving Castle'.",
            posterUrl = "https://upload.wikimedia.org/wikipedia/en/a/a0/Howls-moving-castleposter.jpg",
        )
    }
}