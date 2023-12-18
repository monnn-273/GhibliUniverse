package com.monika.ghibliuniverse.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monika.ghibliuniverse.ui.theme.GhibliUniverseTheme

@Composable
fun MyProfileScreen(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        // Rounded square image
        Image(
            painter = painterResource(id = com.monika.ghibliuniverse.R.drawable.img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(0.dp, 50.dp, 0.dp, 15.dp)
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp)) // Set the corner shape here
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Name text
        Text(
            text = "Monika Angelia Panjaitan",
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Email text
        Text(
            text = "monikapanjaitan03@gmail.com",
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 15.dp),
            textAlign = TextAlign.Center
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)) {
                        append("🎧 Current Favorite Song:\n")
                    }
                    withStyle(style = SpanStyle(fontSize = 14.sp)) {
                        append(" テリフリアメ by Ichiko Aoba")
                    }
                    append("\n\n")
                    withStyle(style = SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold,  fontStyle = FontStyle.Italic)) {
                        append("📖 Current Favorite Book:\n")
                    }
                    withStyle(style = SpanStyle(fontSize = 14.sp)) {
                        append("The Miracles of the Namiya General Store")
                    }
                    append("\n\n")
                    withStyle(style = SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)) {
                        append("🫧 Current Worries:\n")
                    }
                    withStyle(style = SpanStyle(fontSize = 14.sp)) {
                        append("In the Kokoro novel by Natsume Soseki, is there any chance that Sensei could be saved?")
                    }
                    append("\n\n")
                    withStyle(style = SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)) {
                        append("🧭 Current Life Compass:")
                    }
                    append("\n")
                    withStyle(style = SpanStyle(fontSize = 14.sp)) {
                        append("There’s something I’ve learned from years of reading people’s letters. In most cases, they already have an answer to their problem. They’re asking for advice because they want to see if other people think they’re making the right decision. That’s why a lot of people send me a response after reading my advice. Maybe they had a different solution in mind. (Keiga Higashino in The Miracles of the Namiya General Store)")
                    }
                },
                modifier = Modifier.padding(16.dp)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyProfileScreenPreview() {
    MyProfileScreen()
}