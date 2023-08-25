package ir.codroid.batmanmovies.ui.screen.list

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import ir.codroid.batmanmovies.data.model.Movie
import ir.codroid.batmanmovies.ui.component.TitleText
import ir.codroid.batmanmovies.ui.theme.MediumGray
import ir.codroid.batmanmovies.ui.theme.textColor
import java.util.Random


@Composable
fun HorizontalMovieList(
    @StringRes title : Int,
    list: List<Movie>,
    onMovieClick :(String) ->Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
        TitleText(
            text = title,
            Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(items = list , key = {
                it.imdbID
            }) {
                TrendsItem(movie = it) {imdbID ->
                    onMovieClick(imdbID)
                }
            }
        }
    }
}

@Composable
fun TrendsItem(movie: Movie , onMovieClick :(String) ->Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .width(150.dp)
            .clickable { onMovieClick(movie.imdbID) }
    ) {
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(data = movie.Poster)
                .apply(
                    block = fun ImageRequest.Builder.() {
                        scale(Scale.FILL)
                    }
                )
                .build()
        )
        Image(
            painter = painter, contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(150.dp)
                .height(200.dp)
                .border(BorderStroke(1.dp, MediumGray), RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))

        )
        Text(
            text = movie.Title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.textColor,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = movie.Type,
            color = MaterialTheme.colorScheme.textColor,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = movie.Year,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.textColor,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        val min = 1f
        val max = 5f
        val random = Random()
        val ratting: Float = min + random.nextFloat() * (max - min)

        RatingBar(
            value = ratting,
            style = RatingBarStyle.Fill(),
            size = 20.dp,
            spaceBetween = 4.dp,
            onValueChange = {
            },
            onRatingChanged = {
                Log.d("TAG", "onRatingChanged: $it")
            }
        )
    }
}