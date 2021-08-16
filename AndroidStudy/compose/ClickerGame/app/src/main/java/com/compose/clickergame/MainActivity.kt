package com.compose.clickergame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.clickergame.ui.theme.ClickerGameTheme

class MainActivity : ComponentActivity() {

    private val vm = ViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClickerGameTheme {
                val score = vm.score.observeAsState().value
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ScoreText(score?:0)
                        ScoreButtons(vm = vm)
                    }
                }
            }
        }
    }
}

@Composable
fun ScoreText(score: Int) {
    Text(text = score.toString(),fontSize = 20.sp)
}

@Composable
fun ScoreButtons(vm:ViewModel){
    Row(horizontalArrangement = Arrangement.SpaceEvenly,modifier = Modifier.fillMaxWidth()) {
        Button(onClick = { vm.plusScore() }) {
            Text(text = "Plus")
        }
        Button(onClick = { vm.resetScore() }) {
            Text(text = "Reset")
        }
        Button(onClick = { vm.minusScore() }) {
            Text(text = "Minus")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ClickerGameTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ScoreText(0)
                ScoreButtons(ViewModel())
            }
        }
    }
}