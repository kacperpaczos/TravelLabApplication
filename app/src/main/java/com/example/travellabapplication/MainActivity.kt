package com.example.travellabapplication

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.travellabapplication.ui.theme.TravelLabApplicationTheme
import android.widget.Toast
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TravelLabApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ButtonList(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ButtonList(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        val numberOfButtons = 5
        val context = LocalContext.current
        for (i in 1..numberOfButtons) {
            Button(
                onClick = {
                    val intent = Intent(context, TravelDetails::class.java)
                    intent.putExtra("buttonNumber", i)
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Przycisk $i")
            }
        }
    }
}

@Composable
fun showToast(message: String) {
    Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun ButtonListPreview() {
    TravelLabApplicationTheme {
        ButtonList()
    }
}