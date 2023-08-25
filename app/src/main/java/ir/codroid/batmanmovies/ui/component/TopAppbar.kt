package ir.codroid.batmanmovies.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ir.codroid.batmanmovies.R
import ir.codroid.batmanmovies.navigation.Screen
import ir.codroid.batmanmovies.ui.theme.MediumGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppbar(
    navController: NavHostController,
    onGitHub: () -> Unit,
    onLinedIn: () -> Unit,
) {
    val topBarScreen = listOf(
        Screen.MovieList,
        Screen.AboutBatman
    )
    val backStackEntry = navController.currentBackStackEntryAsState()
    val showTopBar =
        backStackEntry.value?.destination?.route in topBarScreen.map { it.route }
    if (showTopBar) {
        TopAppBar(
            navigationIcon = {},
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            },
            actions = {
                MenuAction(onGitHub = onGitHub, onLinedIn = onLinedIn)
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                titleContentColor = MediumGray,
                actionIconContentColor = MediumGray,
            )
        )
    }
}

@Composable
fun MenuAction(
    onGitHub: () -> Unit,
    onLinedIn: () -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = null
        )
    }
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(id = R.string.git_hub),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                )

            },
            onClick = {
                onGitHub()
                expanded = false
            }
        )
        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(id = R.string.linked_in),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                )

            },
            onClick = {
                onLinedIn()
                expanded = false
            }
        )

    }
}