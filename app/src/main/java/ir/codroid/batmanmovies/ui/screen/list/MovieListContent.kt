package ir.codroid.batmanmovies.ui.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import ir.codroid.batmanmovies.R
import ir.codroid.batmanmovies.data.model.Movie
import ir.codroid.batmanmovies.ui.component.ViewPagerSlider
import ir.codroid.batmanmovies.ui.theme.backgroundColor

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MovieListContent(
    list: List<Movie>,
    onMovieClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 64.dp),
        content = {
            item {
                ViewPagerSlider(movieList = list){imdbID -> onMovieClick(imdbID)}
                HorizontalMovieList(R.string.trends, list = list){imdbID -> onMovieClick(imdbID)}
                HorizontalMovieList(R.string.action, list = list.asReversed()){imdbID -> onMovieClick(imdbID)}
                HorizontalMovieList(R.string.twenty_one_st_century, list = list.filter { it.Year.contains("20") }){imdbID -> onMovieClick(imdbID)}
            }
        })
}