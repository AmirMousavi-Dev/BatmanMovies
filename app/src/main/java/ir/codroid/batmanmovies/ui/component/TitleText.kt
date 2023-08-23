package ir.codroid.batmanmovies.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ir.codroid.batmanmovies.ui.theme.exoExtraBold
import ir.codroid.batmanmovies.ui.theme.textColor

@Composable
fun TitleText(
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = text),
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier,
        fontFamily = exoExtraBold ,
        color = MaterialTheme.colorScheme.textColor
    )
}