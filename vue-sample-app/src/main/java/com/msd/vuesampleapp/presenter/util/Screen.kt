package com.msd.vuesampleapp.presenter.util

sealed class Screen(val Route: String){
    object MainScreen : Screen("user_setup_screen")
    object SelectUserScreen : Screen("select_user_screen")
    object TestingScreen1 : Screen("test_1_screen")
    object TestingScreen2 : Screen("test_2_screen")
}
