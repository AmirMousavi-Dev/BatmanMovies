package ir.codroid.batmanmovies.navigation

import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Straight
import com.exyte.animatednavbar.animation.indendshape.StraightIndent
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import ir.codroid.batmanmovies.ui.theme.bottomBarColor
import ir.codroid.batmanmovies.ui.theme.bottomBarDeSelectedItemColor
import ir.codroid.batmanmovies.ui.theme.bottomBarSelectedItemColor

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {

    val navigationBarItem = remember {
        BottomNavItem.values()
    }
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    val backStackEntry = navController.currentBackStackEntryAsState()
    val showBottomBar =
        backStackEntry.value?.destination?.route in navigationBarItem.map { it.route }

    if (showBottomBar) {
        AnimatedNavigationBar(
            selectedIndex = selectedIndex,
            modifier = modifier.height(64.dp),
            ballAnimation = Straight(tween(500)),
            indentAnimation = StraightIndent(tween(300)),
            barColor = MaterialTheme.colorScheme.bottomBarColor,
            ballColor = MaterialTheme.colorScheme.bottomBarColor
        ) {
            navigationBarItem.forEachIndexed { index, item ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .noRippleClickable {
                            selectedIndex = item.ordinal
                            onItemClick(item)
                            selectedIndex = index} ,
                    contentAlignment = Alignment.Center) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = item.icon),
                        contentDescription = item.name,
                        tint = if (selectedIndex == item.ordinal) MaterialTheme.colorScheme.bottomBarSelectedItemColor
                        else MaterialTheme.colorScheme.bottomBarDeSelectedItemColor
                    )
                }
            }
        }
    }

}

private fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember {
            MutableInteractionSource()
        }
    ) {
        onClick()
    }
}