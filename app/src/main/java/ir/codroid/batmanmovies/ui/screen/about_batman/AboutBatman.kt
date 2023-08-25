package ir.codroid.batmanmovies.ui.screen.about_batman

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ir.codroid.batmanmovies.R
import ir.codroid.batmanmovies.ui.theme.exoExtraBold
import ir.codroid.batmanmovies.ui.theme.primaryColor
import ir.codroid.batmanmovies.ui.theme.textColor

@Composable
fun AboutBatman() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 64.dp , horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        item {


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier.size(180.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.batman), contentDescription = "",
                        modifier = Modifier
                            .size(180.dp)
                            .clip(CircleShape)
                            .border(
                                BorderStroke(2.dp, MaterialTheme.colorScheme.primaryColor),
                                CircleShape
                            ),
                        contentScale = ContentScale.Crop
                    )
                }

            }


            Text(
                text = stringResource(id = R.string.batman_name),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(8.dp),
                fontFamily = exoExtraBold,
                color = MaterialTheme.colorScheme.textColor
            )
            Text(
                text = stringResource(id = R.string.batman_bio),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colorScheme.textColor

            )
            AboutMeCustomInfo(
                title = R.string.allies,
                R.string.alfred,
                R.string.batman_family,
                R.string.justice_league,
                R.string.robin
            )
            AboutMeCustomInfo(
                title = R.string.enemies,
                R.string.enemies_desc
            )
            AboutMeCustomInfo(
                title = R.string.family_tree,
                R.string.family_tree_desc,
            )
        }
    }
}

@Composable
fun AboutMeCustomInfo(
    @StringRes title: Int,
    @StringRes vararg desc: Int,
) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {

        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium,
            fontFamily = exoExtraBold,
            color = MaterialTheme.colorScheme.textColor

        )
        desc.forEach {

            Text(
                text = stringResource(id = it),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.textColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify

            )
            Spacer(modifier = Modifier.height(4.dp))
        }

    }
}

@Preview
@Composable
fun PrwAboutMe() {
    AboutBatman()
}