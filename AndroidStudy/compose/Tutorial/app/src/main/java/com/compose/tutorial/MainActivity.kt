package com.compose.tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.compose.tutorial.ui.theme.TutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TutorialTheme {

                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column{
        Text(text = "Hello $name!")
        Text("Hello Compose")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TutorialTheme {
        Text("Hello Compose")
        Greeting("Android")
    }
}