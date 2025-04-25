package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.test.ui.theme.TestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val keyPads = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("*", "0", "#"),
    )

    BoxWithConstraints {

        val buttonSize = minOf(maxWidth, maxHeight) / 4
        var textDisplay by rememberSaveable { mutableStateOf("") }
        val textSize = buttonSize.value * 0.3

        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = textDisplay,
                    modifier = Modifier.padding(16.dp).height(buttonSize/2),
                    fontSize = textSize.sp
                )
            }

//            Spacer(modifier = Modifier.padding(8.dp))

            keyPads.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    row.forEach { key ->
//                        Text(text = key, modifier = modifier.padding(16.dp))
                        KepPadButton(
                            key = key,
                            size = buttonSize,
                            textSize = textSize,
                            onClick = { keyValue ->
                                textDisplay += keyValue
                            }
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                KepPadButton(
                    key = "x",
                    size = buttonSize,
                    textSize = textSize,
                    onClick = { _ ->
                        if (textDisplay.isNotEmpty()) {
                            textDisplay = textDisplay.dropLast(1)
                        }
                    }
                )
            }
        }

    }


}

@Composable
fun KepPadButton(key: String, size: Dp, onClick: (String) -> Unit, textSize: Double) {
    Button(
        onClick = { onClick(key) },
        modifier = Modifier
            .padding(8.dp)
            .size(size),
        shape = CircleShape
    ) {
        Text(text = key, fontSize = textSize.sp)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    TestTheme {
//        Greeting("Android")
//    }
//}