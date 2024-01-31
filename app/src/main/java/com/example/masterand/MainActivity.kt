package com.example.masterand

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.masterand.ui.theme.MasterAndTheme
import com.example.masterand.views.MainGame
import com.example.masterand.views.ProfileScreenInitial
import com.example.masterand.views.ResultScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            MasterAndTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "ProfileScreen") {
                        composable(
                            route = "ProfileScreen",
                            enterTransition = {
                                slideInHorizontally(
                                    animationSpec = tween(1000, easing = EaseIn),
                                    initialOffsetX = { it }
                                )
                            },
                            exitTransition = {
                                slideOutHorizontally(
                                    animationSpec = tween(1000, easing = EaseOut),
                                    targetOffsetX = { it }
                                )
                            }
                        ) {
                            ProfileScreenInitial(
                                onStartGame = { numberOfColours, playerName ->
                                    navController.navigate("MainGame/$numberOfColours/$playerName")
                                }
                            )
                        }
                        composable(
                            route = "MainGame/{numberOfColours}/{playerName}",
                            arguments = listOf(
                                navArgument("numberOfColours") { type = NavType.StringType },
                                navArgument("playerName") { type = NavType.StringType }),
                            enterTransition = {
                                slideInHorizontally(
                                    animationSpec = tween(1000, easing = EaseIn),
                                    initialOffsetX = { it }
                                )
                            },
                            exitTransition = {
                                slideOutHorizontally(
                                    animationSpec = tween(1000, easing = EaseOut),
                                    targetOffsetX = { it }
                                )
                            }
                        ) {
                            MainGame(
                                numberOfColours = it.arguments?.getString("numberOfColours") ?: "0",
                                playerName = it.arguments?.getString("playerName") ?: "no user",
                                onScoreScreen = { score, oldNumberOfColours, playerName ->
                                    navController.navigate("ResultScreen/$score/$playerName/$oldNumberOfColours")
                                },
                                onLogout = {
                                    navController.navigate("ProfileScreen")
                                })

                        }
                        composable(
                            route = "ResultScreen/{score}/{playerName}/{oldNumberOfColours}",
                            arguments = listOf(
                                navArgument("score") { type = NavType.StringType },
                                navArgument("playerName") { type = NavType.StringType },
                                navArgument("oldNumberOfColours") { type = NavType.StringType },),
                            enterTransition = {
                                slideInHorizontally(
                                    animationSpec = tween(1000, easing = EaseIn),
                                    initialOffsetX = { it }
                                )
                            },
                            exitTransition = {
                                slideOutHorizontally(
                                    animationSpec = tween(1000, easing = EaseOut),
                                    targetOffsetX = { it }
                                )
                            }
                        ) {
                            ResultScreen(
                                score = it.arguments?.getString("score") ?: "0",
                                playerName = it.arguments?.getString("playerName") ?: "no user",
                                oldNumberOfColours = it.arguments?.getString("oldNumberOfColours") ?: "5",
                                onRestartGame = { oldNumberOfColours, playerName ->
                                    navController.navigate("MainGame/$oldNumberOfColours/$playerName")
                                },
                                onLogout = {
                                    navController.navigate("ProfileScreen")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MasterAndPreview() {
    MasterAndTheme {}
}