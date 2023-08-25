package ir.codroid.batmanmovies

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
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
import ir.codroid.batmanmovies.navigation.Screen
import ir.codroid.batmanmovies.navigation.SetupNavigation
import ir.codroid.batmanmovies.ui.component.ChangeSystemUi
import ir.codroid.batmanmovies.ui.component.TopAppbar
import ir.codroid.batmanmovies.ui.theme.BatmanMoviesTheme
import ir.codroid.batmanmovies.util.Constants.GITHUB_URL
import ir.codroid.batmanmovies.util.Constants.LINKEDIN_URL


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
                ChangeSystemUi(navController)
                Scaffold(
                    topBar = {
                        TopAppbar(
                            navController = navController,
                            onGitHub = {
                                startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(GITHUB_URL)
                                    )
                                )
                            },
                            onLinedIn = {
                                startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW, Uri.parse(
                                            LINKEDIN_URL
                                        )
                                    )
                                )
                            })
                    },
                    content = {
                        SetupNavigation(navController = navController)
                    },
                    bottomBar = {
                        BottomNavigationBar(navController = navController) {
                            navController.navigate(it.route) {
                                popUpTo(
                                    navController.currentDestination?.route
                                        ?: Screen.MovieList.route
                                ) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}