package ir.codroid.batmanmovies.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val LightGray = Color(0xFFFCFCFC)
val MediumGray = Color(0xFF8F8F8F)
val DarkGray = Color(0xFF141414)

val lightBackgroundColor = Color(0xFFFDFDFD)
val darkBackgroundColor = Color(0xFF161616)

val lightTextColor = Color(0xFF16205F)
val darkTextColor = Color(0xFFE4E4E4)

// region Top Appbar Color
val ColorScheme.backgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) darkBackgroundColor else lightBackgroundColor
val ColorScheme.textColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) darkTextColor else lightTextColor
// endregion Top Appbar Color

// region List Item  Color
val ColorScheme.listItemBackgroundColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) DarkGray else Color.White
val ColorScheme.listItemTextColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray else DarkGray
// endregion List Item Background Color

val ColorScheme.splashBGColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) DarkGray else Color.White