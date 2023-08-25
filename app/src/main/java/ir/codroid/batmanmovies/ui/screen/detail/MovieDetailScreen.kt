package ir.codroid.batmanmovies.ui.screen.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import ir.codroid.batmanmovies.data.model.Movie
import ir.codroid.batmanmovies.data.model.MovieDetail
import ir.codroid.batmanmovies.data.remote.NetWorkResult
import ir.codroid.batmanmovies.navigation.Screen
import ir.codroid.batmanmovies.ui.SharedViewModel
import ir.codroid.batmanmovies.ui.component.ErrorScreen
import ir.codroid.batmanmovies.ui.component.LoadingCircle
import ir.codroid.batmanmovies.ui.theme.LightGray
import ir.codroid.batmanmovies.ui.theme.MediumGray
import ir.codroid.batmanmovies.ui.theme.primaryColor
import ir.codroid.batmanmovies.ui.theme.textColor
import kotlin.math.max
import kotlin.math.min

@Composable
fun MovieDetailScreen(
    navController: NavHostController,
    imdbID: String,
    viewModel: SharedViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.getMovieDetail(imdbID)
        viewModel.getSimilarMovie()
    }
    var loading by remember {
        mutableStateOf(true)
    }
    var error by remember {
        mutableStateOf(false)
    }
    val result = viewModel.movieDetail.collectAsState()
    val similarMovies = viewModel.similarMovie.collectAsState()
    val movie = result.value.data
    val scrollState = rememberLazyListState()

    result.value.let {
        when (result.value) {
            is NetWorkResult.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    LoadingCircle(isSystemInDarkTheme(), loading)
                }
            }

            is NetWorkResult.Success -> {
                movie?.let {
                    var isFavorite by remember {
                        mutableStateOf(false)
                    }
                    Box {
                        MovieDetailContent(
                            scrollState,
                            movieDetail = it,
                            similarMovies.value
                        )  {
                            navController.navigate(Screen.MovieDetail.withArgs(it))
                        }
                        MovieDetailParallaxToolBar(
                            scrollState,
                            movieDetail = it,
                            onBackClick = { navController.popBackStack() },
                            isFavorite = isFavorite
                        ) {
                            isFavorite = !isFavorite

                        }
                    }
                }
                loading = false
            }

            is NetWorkResult.Error -> {
                loading = false
                error = true
                ErrorScreen(error)
            }
        }

    }


}

@Composable
fun MovieDetailContent(
    scrollState: LazyListState,
    movieDetail: MovieDetail,
    similarMovies: List<Movie>,
    onSimilarItemClick : (String) ->Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(top = appBarExpandHeight),
        state = scrollState
    ) {
        item {
            Column {
                BasicInfo(movieDetail.Ratings)
                Text(
                    text = movieDetail.Plot,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Justify,
                    color = MaterialTheme.colorScheme.textColor,
                    modifier = Modifier.padding(16.dp)
                )
                MoreInfo(movieDetail)

                SimilarMovie(similarMovies){
                    onSimilarItemClick(it)
                }


            }
        }
    }
}

val appBarCollapseHeight = 64.dp
val appBarExpandHeight = 500.dp

@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun MovieDetailParallaxToolBar(
    scrollState: LazyListState,
    movieDetail: MovieDetail,
    isFavorite: Boolean,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit,
) {
    val imageHeight = appBarExpandHeight - appBarCollapseHeight
    val maxOffset = with(LocalDensity.current) {
        imageHeight.roundToPx() - LocalWindowInsets.current.statusBars.layoutInsets.top
    }
    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)

    val offsetProgress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset
    TopAppBar(
        contentPadding = PaddingValues(),
        backgroundColor = Color.White,
        modifier = Modifier
            .height(appBarExpandHeight)
            .offset { IntOffset(x = 0, y = -offset) },
        elevation = if (offset == maxOffset) 4.dp else 0.dp
    ) {
        Column() {
            Box(
                modifier = Modifier
                    .height(imageHeight)
                    .graphicsLayer { alpha = 1f - offsetProgress })
            {
                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = movieDetail.Poster)
                        .apply(
                            block = fun ImageRequest.Builder.() {
                                scale(Scale.FILL)
                            }
                        )
                        .build()
                )
                Image(
                    painter = painter,
                    contentDescription = movieDetail.Title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colorStops = arrayOf(
                                    Pair(0.4f, Color.Transparent),
                                    Pair(1f, MaterialTheme.colorScheme.surface)
                                )
                            )
                        )
                )
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = movieDetail.Type,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(4.dp)
                            )
                            .background(MaterialTheme.colorScheme.primaryColor)
                            .padding(horizontal = 16.dp, vertical = 8.dp),

                        )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(appBarCollapseHeight)
                    .background(MaterialTheme.colorScheme.surface),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = movieDetail.Title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.textColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(
                            horizontal = (16 + 28 * offsetProgress).dp
                        )
                        .scale(1f - 0.25f * offsetProgress)
                )
            }
        }

    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(appBarCollapseHeight)
            .padding(horizontal = 16.dp)
    ) {
        CircularButton(icon = Icons.Default.ArrowBack) {
            onBackClick()
        }
        CircularButton(
            icon = Icons.Default.Favorite,
            contentColor = if (isFavorite) Color.Red else Color.Gray
        ) {
            onFavoriteClick()
        }
    }
}

@Composable
fun CircularButton(
    icon: ImageVector,
    contentColor: Color = Color.Gray,
    backgroundColor: Color = Color.White,
    size: Dp = 38.dp,
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        elevation = elevation,
        modifier = Modifier.size(size)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            modifier = Modifier.size(size)
        )
    }
}