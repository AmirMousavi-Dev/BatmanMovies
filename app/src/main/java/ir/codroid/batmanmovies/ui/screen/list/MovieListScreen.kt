package ir.codroid.batmanmovies.ui.screen.list

import android.util.Log
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import ir.codroid.batmanmovies.data.remote.NetWorkResult
import ir.codroid.batmanmovies.ui.SharedViewModel
import ir.codroid.batmanmovies.ui.component.ViewPagerSlider
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MovieListScreen(
    navController: NavHostController,
    viewModel: SharedViewModel = hiltViewModel()
) {
    LaunchedEffect(true){
    viewModel.getMovieList()

    }

    val result = viewModel._movieList.collectAsState()
    val movies = result.value.data

    when(result.value) {
        is NetWorkResult.Loading -> {}
        is NetWorkResult.Success -> {
            movies?.let {
                if (it.isEmpty()){
                    Toast.makeText(LocalContext.current, "empty", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(LocalContext.current, it[0].Title, Toast.LENGTH_SHORT).show()

                }
                ViewPagerSlider(movieList = it)
            }
        }
        is NetWorkResult.Error -> {
            Log.e("3121" , "error")

        }
    }


}