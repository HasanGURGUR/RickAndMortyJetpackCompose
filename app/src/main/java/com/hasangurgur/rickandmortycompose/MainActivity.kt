package com.hasangurgur.rickandmortycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.hasangurgur.rickandmortycompose.presentation.character_list.CharacterScreen
import com.hasangurgur.rickandmortycompose.presentation.navigation.NavGraph
import com.hasangurgur.rickandmortycompose.ui.theme.RickAndMortyComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyComposeTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}





