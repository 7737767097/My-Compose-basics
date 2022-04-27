package com.demo.mybasiccompose1

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.demo.mybasiccompose1.ui.theme.MyBasicCompose1Theme

class MainActivity : ComponentActivity() {

    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBasicCompose1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                    MyExpandedList(Modifier) { MySingleChildView(Modifier.fillMaxSize()) }
                }
            }
        }
    }

    lateinit var textView: TextView

    override fun onResume() {
        super.onResume()
        "Commit 4"
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyBasicCompose1Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
            }
        }
    }
}

@Composable
fun MySingleChildView(modifier: Modifier) {
    Column(
        modifier = modifier //        Modifier
//            .fillMaxWidth()
//            .background(Color.LightGray)
//            .padding(16.dp)
    ) {
        Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
    }
}

@Composable
fun MyLazyListView(listSize: Int = 10) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
        ) {
            items(listSize) {
                if (it % 2 == 0) {
                    Text(text = "I am even")
                    if (it < listSize - 1) Divider(color = Color.Blue, thickness = 1.dp)
                } else {
                    Text(text = "I am odd")
                    if (it < listSize - 1) Divider(color = Color.Black, thickness = 1.dp)
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun MyExpandedList(
    modifier: Modifier = Modifier,
    content: @Composable() () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val rotateState = animateFloatAsState(targetValue = if (expanded) 180F else 0F)
    Column(modifier = Modifier.fillMaxWidth()) {
        Card(onClick = { expanded = !expanded }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "This is header",
                    modifier = Modifier.fillMaxWidth(0.92F),
                    style = MaterialTheme.typography.subtitle1
                )
                Icon(Icons.Default.ArrowDropDown, "", modifier = Modifier.rotate(rotateState.value))
            }
        }
        Divider()
        AnimatedVisibility(visible = expanded) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.background
            ) {
                Column {
                    content()
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun expendedList() {
    var extended by remember { mutableStateOf(false) }
    val rotateState = animateFloatAsState(targetValue = if (extended) 180F else 0F)
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Card(onClick = { extended = !extended }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "This is header",
                        modifier = Modifier.fillMaxWidth(0.92F),
                        style = MaterialTheme.typography.subtitle1
                    )
                    Icon(
                        Icons.Default.ArrowDropDown, "",
                        modifier = Modifier.rotate(rotateState.value)
                    )
                }
            }
            Divider()
            AnimatedVisibility(visible = extended) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(16.dp)
                ) {
                    Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.")
                }
            }
        }
    }
}
