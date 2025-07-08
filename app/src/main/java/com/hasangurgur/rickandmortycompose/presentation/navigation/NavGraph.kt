package com.hasangurgur.rickandmortycompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hasangurgur.rickandmortycompose.presentation.character_detail.CharacterDetailScreen
import com.hasangurgur.rickandmortycompose.presentation.character_list.CharacterScreen


sealed class Screen(val route: String) {
    object CharacterList : Screen("character_list")
    object CharacterDetail : Screen("character_detail/{characterId}") {
        fun passCharacterId(id: Int): String = "character_detail/$id"
    }
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.CharacterList.route
    ) {
        composable(route = Screen.CharacterList.route){
            CharacterScreen(navController = navController)
        }

        composable(route = Screen.CharacterDetail.route) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull()
            characterId?.let {
                CharacterDetailScreen(characterId = it)
            }
        }


    }
}