package com.monika.ghibliuniverse.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyProfileScreen(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = com.monika.ghibliuniverse.R.drawable.img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(0.dp, 50.dp, 0.dp, 15.dp)
                .size(200.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Monika Angelia Panjaitan",
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = "monikapanjaitan03@gmail.com",
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(0.dp,5.dp,0.dp,15.dp),
            textAlign = TextAlign.Center
        )
    }
}