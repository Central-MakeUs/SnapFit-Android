package memory.fabricators.snapfit.ui.artist.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ArtistDetailsScreen(
    modifier: Modifier = Modifier,
) {
    // TODO
    val images = listOf(
        ArtistImage(
            id = "1",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR14NETh5SYT4HXp3KvnmgmvB5xNqj0a1oEKw&s",
        ),
        ArtistImage(
            id = "2",
            imageUrl = "https://wallpapers.com/images/hd/best-hd-autumn-leaves-57tbt9tq3xf3vdm2.jpg",
        ),
        ArtistImage(
            id = "3",
            imageUrl = "https://static.vecteezy.com/system/resources/previews/030/355/618/non_2x/flowers-in-the-field-mountains-flowers-nature-nature-hd-wallpaper-ai-generated-free-photo.jpg",
        ),
        ArtistImage(
            id = "4",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRrlfYAIT-nlIWL5KaDn4ngk1avOoSbitiwow&s",
        ),
    )
    val pagerState = rememberPagerState {
        images.size
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { /*TODO*/ },
                navigationIcon = {
                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "back",
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "favorite",
                        )
                    }
                    IconButton(
                        onClick = { /*TODO*/ },
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreHoriz,
                            contentDescription = "more",
                        )
                    }
                },
            )
        },
    ) { innerPaddings ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings),
        ) {
            HorizontalPager(
                modifier = Modifier.fillMaxWidth(),
                state = pagerState,
                key = { images[it].id },
            ) { page ->
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    // TODO: placeholder = painterResource(id = R.drawable.img_start_background),
                    contentScale = ContentScale.Crop,
                    model = images[page].imageUrl,
                    contentDescription = null,
                )
            }
        }
    }
}

data class ArtistImage(
    val id: String,
    val imageUrl: String,
)
