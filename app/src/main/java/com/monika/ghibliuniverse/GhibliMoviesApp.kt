package com.monika.ghibliuniverse

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.monika.ghibliuniverse.ui.navigation.NavigationItem
import com.monika.ghibliuniverse.ui.navigation.Screen
import com.monika.ghibliuniverse.ui.screen.DetailScreen
import com.monika.ghibliuniverse.ui.screen.HomeScreen
import com.monika.ghibliuniverse.ui.screen.MyProfileScreen

@Composable
fun GhibliMoviesApp(
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold (
        bottomBar = {
            if (currentRoute != Screen.Detail.route) {
                BottomBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController =  navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { movieId ->
                        navController.navigate(Screen.Detail.createRoute(movieId))
                    }
                )
            }
            composable(
                route = Screen.MyProfile.route) {
                MyProfileScreen()
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("MovieId") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("MovieId") ?: 0
                DetailScreen(
                    MovieId = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier =  Modifier
) {
    NavigationBar (
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.MyProfile
            ),
        )
        navigationItems.map {
            NavigationBarItem (
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title
                    )
                },
                label = {Text (it.title)},
                selected = currentRoute == it.screen.route,
                onClick = {
                    navController.navigate(it.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}