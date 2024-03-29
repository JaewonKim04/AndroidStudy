package com.compose.tutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.tutorial.ui.theme.TutorialTheme

class MainActivity : ComponentActivity() {

    private val vm = ViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TutorialTheme {
                val persons = vm.persons.observeAsState().value!!
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ColumnText("Android")
                        RowText()
                        Conversation(persons = persons)
                    }
                }

            }

        }
        vm.setPerson()
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp), horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Row")
        Text(text = "Test")
    }
}

@Composable
fun PersonCard(person: Person) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_picture),
            contentDescription = "Person profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Surface(shape = MaterialTheme.shapes.medium,elevation = 1.dp) {
            Column(modifier = Modifier.padding(4.dp)) {
                SetNameText(name = person.name)
                Spacer(modifier = Modifier.height(4.dp))
                SetAgeText(age = person.age)
            }
        }

    }
}

@Composable
fun SetAgeText(age: Int) {
    Text(text = "나이: $age 세", style = MaterialTheme.typography.body1)
}

@Composable
fun SetNameText(name: String) {
    Text(
        text = "이름: $name",
        color = MaterialTheme.colors.secondaryVariant,
        style = MaterialTheme.typography.subtitle1
    )
}

@Composable
fun Conversation(persons:ArrayList<Person>){
    LazyColumn{
        items(persons){person->
            PersonCard(person = person)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TutorialTheme {

        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            PersonCard(person = Person("Test", 18))
        }
    }
}