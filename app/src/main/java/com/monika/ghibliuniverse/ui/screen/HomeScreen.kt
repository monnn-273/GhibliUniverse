package com.monika.ghibliuniverse.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.monika.ghibliuniverse.R
import com.monika.ghibliuniverse.model.GhibliMovies
import com.monika.ghibliuniverse.ui.common.UiState
import com.monika.ghibliuniverse.viewmodel.HomeScreenViewModel
import com.monika.ghibliuniverse.ui.screen.MyEmptyList
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navigateToDetail: (Int) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
        when(it) {
            is UiState.Loading -> {
                viewModel.searchMovie(query)
            }
            is UiState.Success -> {
                HomeContent(
                    listMovie = it.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {
                MyEmptyList()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    listMovie: List<GhibliMovies>,
    navigateToDetail: (Int) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val query by viewModel.query

    // Use AnimatedVisibility to wrap the entire content
    AnimatedVisibility(
        visible = true, // Set the appropriate visibility condition
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically(),
    ) {
        Column {
            // SearchBar wrapped in a Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                SearchBar(
                    query = query,
                    onQueryChange = viewModel::searchMovie
                )
            }

            Box(modifier = modifier) {
                val scope = rememberCoroutineScope()
                val listState = rememberLazyListState()
                val showButton: Boolean by remember {
                    derivedStateOf { listState.firstVisibleItemIndex > 0 }
                }

                LazyColumn(
                    modifier = modifier,
                    contentPadding = PaddingValues(bottom = 80.dp)
                ) {
                    items(listMovie) { movie ->
                        MovieListItem(
                            title = movie.title,
                            posterUrl = movie.posterUrl,
                            caption = movie.caption,
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateItemPlacement(tween(durationMillis = 100))
                                .clickable { navigateToDetail(movie.id) }
                        )
                    }
                }

                // Move AnimatedVisibility to wrap the ScrollToTopButton
                this@Column.AnimatedVisibility(
                    visible = showButton,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically(),
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    ScrollToTopButton(
                        onClick = {
                            scope.launch {
                                listState.scrollToItem(index = 0)
                            }
                        }
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieListItem(
    title: String,
    posterUrl: String,
    caption: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .clickable {}
                .padding(4.dp)
        ) {
            AsyncImage(
                model = posterUrl,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(80.dp, 120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .wrapContentWidth()
                )

                Text(
                    text = caption,
                    modifier = Modifier
                        .wrapContentWidth()
                )
            }
        }
    }

}

//Scroll to Top Button
@Composable
fun ScrollToTopButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilledIconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            contentDescription = stringResource(R.string.scroll_to_top),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        placeholder = {
            Text(stringResource(R.string.search_movie))
        },
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}