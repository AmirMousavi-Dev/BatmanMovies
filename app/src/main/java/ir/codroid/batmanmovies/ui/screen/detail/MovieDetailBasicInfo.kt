package ir.codroid.batmanmovies.ui.screen.detail

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ir.codroid.batmanmovies.R
import ir.codroid.batmanmovies.data.model.MovieDetail
import ir.codroid.batmanmovies.ui.theme.primaryColor


@Composable
fun BasicInfo(ratingList: List<MovieDetail.Rating>) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        ratingList.forEach { ratting ->
            BasicInfoColumn(rating = ratting)
        }
    }
}

@Composable
fun BasicInfoColumn(
    rating: MovieDetail.Rating
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(
                id = when (rating.Source) {
                    "Metacritic" -> R.drawable.ic_metacritic
                    "Internet Movie Database" -> R.drawable.ic_imdb
                    else -> R.drawable.ic_rotton
                }
            ), contentDescription = "",
            modifier = Modifier.size(36.dp),
            tint = MaterialTheme.colorScheme.primaryColor
        )
        Text(text = rating.Value, fontWeight = FontWeight.Bold)
    }
}