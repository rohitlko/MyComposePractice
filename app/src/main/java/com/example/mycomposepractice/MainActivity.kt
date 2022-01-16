package com.example.mycomposepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycomposepractice.ui.theme.MyComposePracticeTheme
import androidx.compose.runtime.getValue

import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                //Greeting("Android")
                MyScreenContent()
            }
        }
    }

}

@Composable
fun MyScreenContent(
    names: List<String> = List(1000) { " Android #$it" },
    desc: List<String> = listOf("rohit", "mohit", "ajay", "tom")
) {
    var counterState by remember {
        mutableStateOf(0)
    }

    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(names, Modifier.weight(1f), desc)
        Counter(
            count = counterState,
            updateCount = { newCount ->
                counterState = newCount
            }

        )



        if (counterState > 5 ){

            Text(text = "I love count")
        }
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {

    // val count = remember { mutableStateOf(0) }
    Button(
        onClick = { updateCount(count + 1) },
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(1f),

        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.Green else MaterialTheme.colors.primary
        )
    ) {
        Text("I've been clicked $count times")
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier, descs: List<String>) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting(name = name)
            Greeting2(desc = "descs")
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MyComposePracticeTheme {
        //Surface(color = Color.Yellow) {
        Surface(color = MaterialTheme.colors.background) {
            // Greeting(name = "Android")
            content()
        }
    }
}

@Composable
fun Greeting(name: String) {
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(
        if (isSelected) MaterialTheme.colors.primary else Color.Transparent,
        animationSpec = tween(durationMillis = 4000)
    )


    Text(
        text = "Hello $name!", modifier = Modifier
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable(onClick = { isSelected = !isSelected })
    )

}

@Composable
fun Greeting2(desc: String) {
    Text(
        text = "$desc", modifier = Modifier
            .padding(end = 24.dp)
            .padding(start = 24.dp)
            .padding(bottom = 6.dp)

    )
}

@Composable
fun DefaultPreview() {
    MyApp {
        Column() {
            Greeting("Android")
            Greeting2("this is rohit")
        }
//        Greeting("Android")
//        Greeting2("this is rohit")
    }
}

@Preview
@Composable
fun DefaultPreviewNew() {
    MyApp {
        MyScreenContent()
    }
}