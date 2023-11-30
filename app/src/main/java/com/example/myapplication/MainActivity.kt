package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CreateCard()
                }
            }
        }
    }
}

@Composable
fun CreateCard(){
    val buttonClickState = remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.White),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(), // modifier match parent
                horizontalAlignment = Alignment.CenterHorizontally // can le giua
            ) {
                Avatar()  // avatar
                Divider(thickness = 1.dp, modifier = Modifier.padding(top = 5.dp)) // the divider
                MyInfo()
                Button(
                    onClick = {
                        buttonClickState.value = !buttonClickState.value
                    },
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.padding(top = 10.dp),
                ) {
                    Text(text = "Show", style = MaterialTheme.typography.titleMedium)
                }

                if(buttonClickState.value){
                    Content()
                } else{
                    Box {}
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun Content(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(6.dp),
//            color = Color.Green,
            border = BorderStroke(width = 2.dp, color = Color.LightGray)
        ) {
            ListInfo(data = listOf("1. This is one","2. This is two","3. This is four"))
        }
    }
}


@Composable
fun ListInfo(data: List<String>) {
    LazyColumn {
        items(data){ item ->
            Card(
                modifier = Modifier
                    .padding(13.dp)
                    .fillMaxWidth(),
                shape = RectangleShape
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(color = MaterialTheme.colorScheme.surface)
                        .fillMaxWidth(),
                    //verticalAlignment = Alignment.CenterVertically
                ) {
                    Avatar(modifier = Modifier.size(100.dp))
                    Column(
                        modifier = Modifier.padding(7.dp).align(Alignment.CenterVertically)
                    ) {
                        Text(text = item, style = MaterialTheme.typography.titleMedium)
                        Text(text = "hehehehehehe", style = MaterialTheme.typography.labelLarge, modifier = Modifier.padding(top = 10.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun MyInfo() {
    Column(
        modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "This is Der",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Blue,
            fontSize = 32.sp,
        )
        Text(
            text = "android jetpack compose",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "@daylader",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.labelMedium
        )
    }
}


@Composable
private fun Avatar(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        shadowElevation = 4.dp, // do bong xung quanh
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.unknown),
            contentDescription = "profile image",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        CreateCard()
    }
}