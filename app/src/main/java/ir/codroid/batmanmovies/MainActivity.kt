package ir.codroid.batmanmovies

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.codroid.batmanmovies.navigation.BottomNavigationBar
import ir.codroid.batmanmovies.navigation.SetupNavigation
import ir.codroid.batmanmovies.ui.screen.list.ListTopAppbar
import ir.codroid.batmanmovies.ui.theme.BatmanMoviesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BatmanMoviesTheme {
                navController = rememberAnimatedNavController()
                Scaffold(
                    topBar = {
                        ListTopAppbar(
                            navController = navController,
                            onGitHub = { /*TODO*/ },
                            onLinedIn = { /*TODO*/ }) {

                        }
                    },
                    content = {
                        SetupNavigation(navController = navController)
                    },
                    bottomBar = {
                        BottomNavigationBar(navController = navController) {

                        }
                    }
                )
            }
        }
    }
}