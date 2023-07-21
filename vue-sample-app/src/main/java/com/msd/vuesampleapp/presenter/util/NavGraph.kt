package com.msd.vuesampleapp.presenter.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.msd.vuesampleapp.presenter.select_user.SelectUserView
import com.msd.vuesampleapp.presenter.test_screens.test_screen_1.TestScreen1
import com.msd.vuesampleapp.presenter.test_screens.test_screen_2.TestScreen2
import com.msd.vuesampleapp.presenter.user_setup.UserSetupView

@Composable
fun NavGraph(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.Route
    ) {
        composable(Screen.MainScreen.Route) {
            UserSetupView(navController)
        }
        composable(Screen.SelectUserScreen.Route){
            SelectUserView(navController)
        }
        composable(Screen.TestingScreen1.Route){
            TestScreen1(navController)
        }
        composable(Screen.TestingScreen2.Route){
            TestScreen2(navController)
        }
    }
}
