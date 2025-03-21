package net.rebbat.mutaabid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import net.rebbat.mutaabid.presentation.view.MainScreen
import net.rebbat.mutaabid.ui.theme.MutaabidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MutaabidTheme(darkTheme = false) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}