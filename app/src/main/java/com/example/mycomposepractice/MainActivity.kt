package com.example.mycomposepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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

//@Composable
//fun MyScreenContent() {
//    Column {
//        Greeting("Android")
//        Divider(color = Color.Black)
//        Greeting("there")
//        Divider(color = Color.Black)
//        Greeting("world")
//
//    }
//}

//@Preview

//@Composable
////fun MyScreenContent(names: List<String> = listOf("rohit","mohit", "ajay", "tom")) {
//fun MyScreenContent(names: List<String> = List(1000) { "Hello Android #$it" }) {
//
//    val counterState = remember { mutableStateOf(0) }
//
//
//    Column(modifier = Modifier.fillMaxHeight()) {
//        Column(modifier = Modifier.weight(1f)) {
//            for (name in names) {
//                Greeting(name = name)
//                Divider(color = Color.Black)
//            }
//        }
//        Divider(color = Color.Transparent, thickness = 33.dp)
//        Counter(
//            count = counterState.value,
//            updateCount = { newCount ->
//                counterState.value = newCount
//            }
//        )
//    }
//}

@Composable
fun MyScreenContent(names: List<String> = List(1000) { "Hello Android #$it" }) {
    val counterState = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(names, Modifier.weight(1f))
        Counter(
            count = counterState.value,
            updateCount = { newCount ->
                counterState.value = newCount
            }
        )
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {

    // val count = remember { mutableStateOf(0) }

    Button(
        onClick = { updateCount(count + 1) },
        modifier = Modifier.padding(24.dp),

        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) Color.Green else Color.White
        )

    ) {
        Text("I've been clicked $count times")
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting(name = name)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MyComposePracticeTheme {
        Surface(color = Color.Yellow) {
            // Greeting(name = "Android")
            content()
        }
    }
}

@Composable
fun Greeting(name: String) {
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)


    Text(text = "Hello $name!", modifier = Modifier
        .padding(24.dp)
        .background(color = backgroundColor)
        .clickable(onClick = { isSelected = !isSelected })
    )

}

//@Preview
@Composable
fun DefaultPreview() {
    MyApp {
        Greeting("Android")
    }
}

@Preview
@Composable
fun DefaultPreviewNew() {
    MyApp {
        MyScreenContent()
    }
}