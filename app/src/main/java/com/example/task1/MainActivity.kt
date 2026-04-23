package com.example.task1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.task1.ui.theme.Task1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val testResults = LampTestRunner.runTests()
        setContent {
            Task1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Button(
                            onClick = {
                                startActivity(Intent(this@MainActivity, CalculatorActivity::class.java))
                            },
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text("Open Calculator")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        TestReport(
                            results = testResults
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TestReport(results: List<String>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Lamp & Bulb Test Results",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn {
            items(results) { result ->
                Text(
                    text = result,
                    modifier = Modifier.padding(vertical = 4.dp),
                    color = if (result.contains("PASS")) 
                        MaterialTheme.colorScheme.primary 
                    else 
                        MaterialTheme.colorScheme.error
                )
                HorizontalDivider()
            }
        }
    }
}
