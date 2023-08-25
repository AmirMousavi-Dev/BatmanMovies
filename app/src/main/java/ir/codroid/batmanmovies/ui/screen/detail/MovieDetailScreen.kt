package ir.codroid.batmanmovies.ui.screen.detail

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
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
import ir.codroid.batmanmovies.R
import ir.codroid.batmanmovies.data.model.Movie
import ir.codroid.batmanmovies.data.model.MovieDetail
import ir.codroid.batmanmovies.data.remote.NetWorkResult
import ir.codroid.batmanmovies.ui.SharedViewModel
import ir.codroid.batmanmovies.ui.theme.MediumGray
import ir.codroid.batmanmovies.ui.theme.exoExtraBold
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
    }
    val result = viewModel._movieDetail.collectAsState()
    val movie = result.value.data
    val scrollState = rememberLazyListState()
    result.value.let {
    if (result.value is NetWorkResult.Success){
        movie?.let {

        Box {
            MovieDetailContent(scrollState, movieDetail = it)
            MovieDetailParallaxToolBar(scrollState, movieDetail = it)
        }
        }
    }

    }


}

@Composable
fun MovieDetailContent(
    scrollState: LazyListState,
    movieDetail: MovieDetail
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

                SimilarMovie(
                    listOf(
                        Movie(
                            "https://m.media-amazon.com/images/M/MV5BYThjYzcyYzItNTVjNy00NDk0LTgwMWQtYjMwNmNlNWJhMzMyXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg",
                            "batman",
                            "movie",
                            "2002",
                            "48"

                        ),
                        Movie(
                            "https://m.media-amazon.com/images/M/MV5BYThjYzcyYzItNTVjNy00NDk0LTgwMWQtYjMwNmNlNWJhMzMyXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg",
                            "batman",
                            "movie",
                            "2002",
                            "48"

                        ),
                        Movie(
                            "https://m.media-amazon.com/images/M/MV5BYThjYzcyYzItNTVjNy00NDk0LTgwMWQtYjMwNmNlNWJhMzMyXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg",
                            "batman",
                            "movie",
                            "2002",
                            "48"

                        ),
                        Movie(
                            "https://m.media-amazon.com/images/M/MV5BYThjYzcyYzItNTVjNy00NDk0LTgwMWQtYjMwNmNlNWJhMzMyXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg",
                            "batman",
                            "movie",
                            "2002",
                            "48"

                        ),
                        Movie(
                            "https://m.media-amazon.com/images/M/MV5BYThjYzcyYzItNTVjNy00NDk0LTgwMWQtYjMwNmNlNWJhMzMyXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg",
                            "batman",
                            "movie",
                            "2002",
                            "48"

                        )
                    )
                )


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
    movieDetail: MovieDetail
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
                                    Pair(1f, Color.White)
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
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(4.dp)
                            )
                            .background(MediumGray)
                            .padding(horizontal = 16.dp, vertical = 8.dp),

                        )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(appBarCollapseHeight),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = movieDetail.Title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
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
        CircularButton(icon = Icons.Default.ArrowBack)
        CircularButton(icon = Icons.Default.Favorite)
    }
}

@Composable
fun CircularButton(
    icon: ImageVector,
    contentColor: Color = Color.Gray,
    backgroundColor: Color = Color.White,
    size: Dp = 38.dp,
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
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