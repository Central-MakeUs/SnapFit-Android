package memory.fabricators.snapfit.ui.mypage

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import memory.fabricators.snapfit.ui.mypage.content.ArtistContent
import memory.fabricators.snapfit.ui.mypage.content.UserContent

@Composable
fun MyPageScreen(
    isArtist: Boolean,
    modifier: Modifier = Modifier,
) {
    if (isArtist) {
        ArtistContent()
    } else {
        UserContent()
    }
}
