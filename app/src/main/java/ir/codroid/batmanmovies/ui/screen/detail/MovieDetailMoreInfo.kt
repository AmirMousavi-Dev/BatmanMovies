package ir.codroid.batmanmovies.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ir.codroid.batmanmovies.R
import ir.codroid.batmanmovies.data.model.MovieDetail
import ir.codroid.batmanmovies.ui.theme.primaryColor
import ir.codroid.batmanmovies.ui.theme.textColor


@Composable
fun MoreInfo(movieDetail: MovieDetail) {
    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        MoreInfoItem(iconRes = R.drawable.ic_director, textRes = R.string.director, text = movieDetail.Director)
        MoreInfoItem(iconRes = R.drawable.ic_writer, textRes = R.string.writer, text = movieDetail.Writer)
        MoreInfoItem(iconRes = R.drawable.ic_actors, textRes = R.string.actors, text = movieDetail.Actors)
        MoreInfoItem(iconRes = R.drawable.ic_genre, textRes = R.string.genre, text = movieDetail.Genre)
        MoreInfoItem(iconRes = R.drawable.ic_rated, textRes = R.string.rated, text = movieDetail.Rated)
        MoreInfoItem(iconRes = R.drawable.ic_language, textRes = R.string.language, text = movieDetail.Language)
        MoreInfoItem(iconRes = R.drawable.ic_earth, textRes = R.string.country, text = movieDetail.Country)
        MoreInfoItem(iconRes = R.drawable.ic_calendar, textRes = R.string.year, text = movieDetail.Year)
        MoreInfoItem(iconRes = R.drawable.ic_awards, textRes = R.string.awards, text = movieDetail.Awards)
    }
}

@Composable
fun MoreInfoItem(
    @DrawableRes iconRes : Int,
    @StringRes textRes: Int,
    text: String
) {
    Row(verticalAlignment = Alignment.CenterVertically , modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Icon(
            painter = painterResource(id = iconRes), contentDescription = stringResource(id = textRes),
            modifier = Modifier
                .size(24.dp),
            tint = MaterialTheme.colorScheme.primaryColor
        )
        Text(text = stringResource(id = textRes, text),  fontWeight = FontWeight.Medium , textAlign = TextAlign.Justify,
            color = MaterialTheme.colorScheme.textColor , modifier = Modifier.padding(start = 8.dp))
    }
}