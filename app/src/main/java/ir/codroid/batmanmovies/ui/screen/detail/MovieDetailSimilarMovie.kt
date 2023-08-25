package ir.codroid.batmanmovies.ui.screen.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import ir.codroid.batmanmovies.data.model.Movie
import ir.codroid.batmanmovies.ui.theme.MediumGray
import ir.codroid.batmanmovies.ui.theme.primaryColor

@Composable
fun SimilarMovieHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            Text(text = "Similar Movie", fontWeight = FontWeight.Bold)
            Text(text = "You can also see there movies ...")
        }
        Button(
            onClick = {}, elevation = null, colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.primaryColor,
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(text = "Show More")
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "")
        }
    }
}


@Composable
fun SimilarMovie(
    movies: List<Movie>,
    onSimilarItemClick : (String) ->Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        SimilarMovieHeader()
        movies.take(3).forEach { movie ->
            SimilarMovieItem(movie){
                onSimilarItemClick(it)
            }
        }
    }
}

@Composable
fun SimilarMovieItem(
    movie: Movie,
    onSimilarItemClick : (String) ->Unit) {
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onSimilarItemClick(movie.imdbID) }
    ) {

        Image(
            painter = painter, contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .weight(0.4f)
                .height(170.dp)
                .border(BorderStroke(1.dp, MediumGray), RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))

        )
        Column(
            modifier = Modifier
                .weight(0.6f)
                .padding(16.dp)
        ) {
            Text(
                text = movie.Title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = movie.Type,
                color = MediumGray,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = movie.Year,
                color = MediumGray,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}