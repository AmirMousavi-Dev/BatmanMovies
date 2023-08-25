package ir.codroid.batmanmovies.ui.screen.list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import ir.codroid.batmanmovies.data.remote.NetWorkResult
import ir.codroid.batmanmovies.navigation.Screen
import ir.codroid.batmanmovies.ui.SharedViewModel
import ir.codroid.batmanmovies.ui.component.ErrorScreen
import ir.codroid.batmanmovies.ui.component.LoadingCircle

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
    var loading by remember {
        mutableStateOf(true)
    }
    var error by remember {
        mutableStateOf(false)
    }
    val result = viewModel.movieList.collectAsState()
    val movies = result.value.data

    when (result.value) {
        is NetWorkResult.Loading -> {
            loading = true
            error = false
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LoadingCircle(isSystemInDarkTheme(), loading)
            }
        }

        is NetWorkResult.Success -> {
            movies?.let {
                MovieListContent(it) { imdbID ->
                    navController.navigate(Screen.MovieDetail.withArgs(imdbID))
                }
                loading = false
                error = false
            }

        }

        is NetWorkResult.Error -> {

            loading = false
            error = true
            ErrorScreen(isShow = error)

        }
    }


}
