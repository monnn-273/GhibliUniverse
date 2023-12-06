package com.monika.ghibliuniverse.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")

    object Detail: Screen("home/{MovieId}") {
        fun createRoute(id: Int) = "home/$id"
    }

    object Profile : Screen("profile")
}