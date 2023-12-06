package com.monika.ghibliuniverse.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.monika.ghibliuniverse.common.UiState
import com.monika.ghibliuniverse.ui.blade.EmpyList
import com.monika.ghibliuniverse.viewmodel.DetailScreenViewModel

@Composable
fun DetailScreen(
    id : Int,
    viewModel: DetailScreenViewModel = hiltViewModel(),
    navigateBack: ()-> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let{
        when (it) {
            is UiState.Loading -> {
                viewModel.getMovieById(id)
            }
            is UiState.Success -> {
                val movie = it.data
                DetailContent (
                    title = movie.title,
                    caption = movie.caption,
                    summary = movie.summary,
                    posterUrl = movie.posterUrl,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {
                EmpyList()
            }
        }
    }
}

@Composable
fun DetailContent(
    title: String,
    caption: String,
    summary : String,
    posterUrl : String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card{
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ){
            Row (
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Top

            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ("back"),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }

            AsyncImage(
                model = posterUrl,
                contentDescription = "drakor_image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(20.dp)
                    .size(250.dp)
            )

            Row (modifier = modifier) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    fontSize = 27.sp,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                Text(
                    text = caption,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 14.dp)
                        .padding(top = 8.dp)

                )
            }

            Text(
                text = summary,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp)
            )
        }
    }
}