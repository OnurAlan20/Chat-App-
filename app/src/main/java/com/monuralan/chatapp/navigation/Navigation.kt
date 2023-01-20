package com.monuralan.chatapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.monuralan.chatapp.screens.LoginScreen
import com.monuralan.chatapp.screens.RegisterScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.LoginScreen.route){
        composable(route =Screens.LoginScreen.route ){
            LoginScreen(navController=navController)
        }
        composable(route =Screens.RegisterScreen.route ){
            RegisterScreen(navController=navController)
        }
    }
}