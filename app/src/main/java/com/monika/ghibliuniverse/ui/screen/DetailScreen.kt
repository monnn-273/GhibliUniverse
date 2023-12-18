package com.monika.ghibliuniverse.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.monika.ghibliuniverse.ui.common.UiState
import com.monika.ghibliuniverse.ui.theme.GhibliUniverseTheme
import com.monika.ghibliuniverse.viewmodel.DetailScreenViewModel

@Composable
fun DetailScreen(
    MovieId : Int,
    viewModel: DetailScreenViewModel = hiltViewModel(),
    navigateBack: ()-> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let{
        when(it) {
            is UiState.Loading -> {
                viewModel.getMovieById(MovieId)
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
                MyEmptyList()
            }
        }
    }
}

@Composable
fun DetailContent(
    title: String,
    caption: String,
    summary: String,
    posterUrl: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ){
        Row(
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
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            ) {
                AsyncImage(
                    model = posterUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = title,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 27.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = caption,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                fontSize = 12.sp,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = summary,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    GhibliUniverseTheme{
        DetailContent(
            "Howl's Moving Castle",
            "Terbanglah bersama dalam petualangan magis di Castle Howl yang Bergerak! Rasakan keajaiban dan kecantikan dunia Miyazaki dalam 'Howl's Moving Castle'.",
            "Genre: Animasi, Petualangan, Fantasi\nSutradara: Hayao Miyazaki\nTahun Rilis: 2004\n\n Howl's Moving Castle\" adalah karya animasi yang diarahkan oleh legenda pembuat film Jepang, Hayao Miyazaki. Ceritanya didasarkan pada novel dengan nama yang sama karya Diana Wynne Jones.\n\nSophie, seorang gadis muda pekerja di toko topi, tanpa sengaja terlibat dalam konflik besar antara dua kerajaan. Akibat kutukan dari penyihir jahat, Sophie mengalami transformasi menjadi seorang wanita tua. Dalam perjalanannya untuk menghilangkan kutukan ini, Sophie bertemu dengan Howl, seorang penyihir muda yang tinggal di sebuah kastil berjalan yang aneh.\n\nKastil tersebut, yang bisa berpindah tempat, penuh dengan keajaiban dan makhluk-makhluk fantastis. Howl, meskipun memiliki kekuatan besar, juga memiliki konflik internal dan terlibat dalam perang yang melibatkan kekuatan sihir. Sophie dan Howl kemudian bersatu untuk menghadapi tantangan yang kompleks dan memecahkan kutukan yang menimpa Sophie.\n\nDengan desain dunia yang kaya, karakter yang mendalam, dan pesan moral yang mendalam, \"Howl's Moving Castle\" tidak hanya memikat penonton dengan keindahan visualnya, tetapi juga menyuguhkan kisah tentang keberanian, cinta, dan pengertian terhadap orang lain.",
        "https://upload.wikimedia.org/wikipedia/en/a/a0/Howls-moving-castleposter.jpg",
            onBackClick = {},
        )
    }
}