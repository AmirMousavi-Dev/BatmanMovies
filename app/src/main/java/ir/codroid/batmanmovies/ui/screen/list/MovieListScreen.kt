package ir.codroid.batmanmovies.ui.screen.list

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.google.accompanist.pager.ExperimentalPagerApi
import ir.codroid.batmanmovies.R
import ir.codroid.batmanmovies.data.model.Movie
import ir.codroid.batmanmovies.data.remote.NetWorkResult
import ir.codroid.batmanmovies.ui.SharedViewModel
import ir.codroid.batmanmovies.ui.component.TitleText
import ir.codroid.batmanmovies.ui.component.ViewPagerSlider
import ir.codroid.batmanmovies.ui.theme.MediumGray
import ir.codroid.batmanmovies.ui.theme.lightTextColor
import ir.codroid.batmanmovies.ui.theme.textColor
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun MovieListScreen(
    navController: NavHostController,
    viewModel: SharedViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.getMovieList()

    }

    val result = viewModel._movieList.collectAsState()
    val movies = result.value.data

    when (result.value) {
        is NetWorkResult.Loading -> {}
        is NetWorkResult.Success -> {
            Scaffold(
                topBar = {
                    ListTopAppbar(onGitHub = { /*TODO*/ }, onLinedIn = { /*TODO*/ }) {

                    }
                },
                content = {

                        movies?.let {
                            if (it.isEmpty()) {
                                Toast.makeText(LocalContext.current, "empty", Toast.LENGTH_SHORT)
                                    .show()

                            } else {
                                Toast.makeText(
                                    LocalContext.current,
                                    it[0].Title,
                                    Toast.LENGTH_SHORT
                                )
                                    .show()

                            }
                            MovieListContent(it)
                        }


                },

                )

        }

        is NetWorkResult.Error -> {
            Log.e("3121", "error")

        }
    }


}

@Composable
fun Trends(list: List<Movie>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TitleText(
            text = R.string.trends,
            Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(items = list , key = {
                it.imdbID
            }) {
                TrendsItem(movie = it)
            }
        }
    }
}

@Composable
fun TrendsItem(movie: Movie) {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .width(200.dp)
    ) {
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(data = movie.Poster)
                .apply(
                    block = fun ImageRequest.Builder.() {
                        scale(Scale.FILL)
                    }
                )
                .build()
        )
        Image(
            painter = painter, contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .border(BorderStroke(1.dp , MediumGray) ,RoundedCornerShape(8.dp) )
                .clip(RoundedCornerShape(8.dp))

        )
        Text(
            text = movie.Title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.textColor,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = movie.Type,
            color = MaterialTheme.colorScheme.textColor,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = movie.Year,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.textColor,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

    }
}