package com.yourcompany.reportmaker.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yourcompany.reportmaker.ui.editor.EditorScreen
import com.yourcompany.reportmaker.ui.home.HomeScreen
import com.yourcompany.reportmaker.ui.pdf.PDFExportScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            HomeScreen(navController = navController)
        }

        composable("editor/{imageId}") { backStackEntry ->
            val imageId = backStackEntry.arguments?.getString("imageId")
            EditorScreen(navController = navController, imageId = imageId)
        }

        composable("pdf") {
            PDFExportScreen(navController = navController)
        }
    }
}
