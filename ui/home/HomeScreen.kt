package com.yourcompany.reportmaker.ui.home

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.asImageBitmap
import androidx.navigation.NavController
import com.yourcompany.reportmaker.ui.viewmodel.HomeViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val images by viewModel.images.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Report Maker") }) },
        floatingActionButton = {
            Column {
                FloatingActionButton(onClick = { /* TODO: Launch CameraX */ }) {
                    Text("📷")
                }
                Spacer(modifier = Modifier.height(8.dp))
                FloatingActionButton(onClick = { /* TODO: Launch Gallery Picker */ }) {
                    Text("🖼️")
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(images) { editableImage ->
                Image(
                    bitmap = editableImage.bitmap.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(8.dp)
                        .clickable {
                            navController.navigate("editor/${editableImage.id}")
                        }
                )
            }
        }
    }
}
