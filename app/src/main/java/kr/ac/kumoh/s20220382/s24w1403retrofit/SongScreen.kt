package kr.ac.kumoh.s20220382.s24w1403retrofit

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

enum class SongScreen {
    Singer,
    Song,
    SongDetail
}

@Composable
fun SingerList() {
    Box (
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = "가수 리스트",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 70.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SongList(viewModel: SongViewModel = viewModel()) {
    val songList by viewModel.songList.observeAsState(emptyList())

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(songList) { song ->
            SongItem(song)
        }
    }
}

@Composable
fun SongDetail() {

}

@Composable
fun SongItem(song: Song) {
    var (expanded, setExpanded) = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .clickable {
                setExpanded(!expanded)
            },
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                //.background(Color(255, 210, 210))
                .padding(8.dp)
        ) {
            AsyncImage(
                model = "https://picsum.photos/300/300?random=${song.singer}",
                contentDescription = "가수 이미지 ${song.singer}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    //.clip(CircleShape),
                    .clip(RoundedCornerShape(percent = 10)),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                TextTitle(song.title)
                TextSinger(song.singer)
            }
        }
        AnimatedVisibility(
            visible = expanded,
            modifier = Modifier.fillMaxWidth(),
        ) {
            song.lyrics?.let {
                Text(
                    it.replace("\\n","\n"),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun TextTitle(title: String) {
    Text(
        title,
        fontSize = 30.sp,
        lineHeight = 35.sp
    )
}

@Composable
fun TextSinger(singer: String) {
    Text(singer, fontSize = 20.sp)
}