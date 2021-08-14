package com.compose.tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.tutorial.ui.theme.TutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TutorialTheme {

                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        ColumnText("Android")
                        RowText()
                    }
                }
            }
        }
    }
}

@Composable
fun ColumnText(name: String) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hello $name!")
        Text("Hello Compose")
    }
}

@Composable
fun RowText() {
    Row (modifier = Modifier.fillMaxWidth().padding(20.dp),horizontalArrangement = Arrangement.SpaceEvenly){
        Text(text = "Row")
        Text(text = "Test")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TutorialTheme {

        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background,modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                ColumnText("Android")
                RowText()
            }
        }
    }
}