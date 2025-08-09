package com.example.rssreader

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rssreader.ui.theme.RSSReaderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        val people = listOf(
//            Person("Hung","Jenny", 25),
//            Person("Lee","Ting", 30),
//            Person("Chen","Judy", 10),
//            Person("Huang","Mark", 21),
//            Person("Bao","Cindy", 20),
//        )
//
//        val peopleFilter = people.filter {
//            it.age >= 25 && it.lastName == "Jenny"
//        }

        val resItems = listOf(
            RSSItem("Welcome to Jenny's blog!","we have a lot of fun here",RSSType.TEXT),
            RSSItem("Welcome to my photo gallery,view photos!","click here for gallery",RSSType.IMAGE,R.drawable.photo_kabi),
            RSSItem("Press conference happening right now","enjoy",RSSType.VIDEO,R.drawable.photo_puri)
        )

        setContent {
            RSSReaderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        LazyColumn {
                            items(resItems) {
                                when (it.type) {
                                    RSSType.TEXT -> {
                                        RSSItemText(it)
                                    }

                                    RSSType.VIDEO -> {
                                        RSSItemVideo(it)
                                    }

                                    RSSType.IMAGE -> {
                                        RSSItemImage(it)
                                    }
                                }
                            }
//                        items(peopleFilter){
//                            CardView(it)
//                        }
                        }
                    }
                    SearchBox()
                }
            }
        }
    }
}
@Composable
fun SearchBox(){
    var searchQuery by remember { mutableStateOf("Search") }

    TextField(
        value = searchQuery,
        onValueChange = {
            searchQuery = it
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    )
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RSSReaderTheme {
        Greeting("Android")
    }
}

@Composable
fun CardView(person: Person){
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.baseline_person_24),
                contentDescription = "Photo of person",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
            Column {
                Text(
                    text = person.firstName,
                    modifier = Modifier.padding(top = 16.dp)//(0.dp)
                )
                Text(
                    text = person.lastName,
                    modifier = Modifier.padding(0.dp)
                )
                Text(
                    text = "Age: " + person.age,
                    modifier = Modifier.padding(0.dp)
                )
            }
        }
    }
}
@Composable
fun RSSItemText(resItem: RSSItem) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = resItem.title,
                fontSize = 32.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(12.dp)
            )
            Text(
                text = resItem.text,
                fontSize = 16.sp,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}
@Composable
fun RSSItemVideo(resItem: RSSItem) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text(
            text = resItem.title,
            fontSize = 32.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(12.dp)
        )
        resItem.media?.let { photo ->
            Image(
                painter = painterResource(id = photo),
                contentDescription = resItem.text,
                modifier = Modifier
//                .width(300.dp)
//                .height(300.dp)
                    .fillMaxSize()
            )
        }
    }
}
@Composable
fun RSSItemImage(resItem: RSSItem) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text(
            text = resItem.title,
            fontSize = 32.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.padding(12.dp)
                .clickable {
                    Log.d("djmalone", "Photo tapped!")
                }
        )
        resItem.media?.let { photo ->
            Image(
                painter = painterResource(id = photo),
                contentDescription = "Photo of person",
                modifier = Modifier
//                .width(300.dp)
//                .height(300.dp)
                    .fillMaxSize()
            )
        }
    }
}
