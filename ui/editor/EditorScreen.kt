package com.yourcompany.reportmaker.ui.editor

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.yourcompany.reportmaker.domain.model.EditableImage
import com.yourcompany.reportmaker.domain.model.TextOverlay
import com.yourcompany.reportmaker.ui.viewmodel.EditorViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun EditorScreen(navController: NavController, imageId: String?, viewModel: EditorViewModel = hiltViewModel()) {

    var newText by remember { mutableStateOf("") }

    val image = viewModel.currentImage.collectAsState().value ?: return

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Editor") },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("pdf")
                    }) {
                        Text("📄")
                    }
                })
        }
    ) { padding ->

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {

            // Canvas for image and overlays
            Canvas(modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, rotation ->
                        // TODO: Apply canvas-wide zoom/pan/rotation if needed
                    }
                }) {

                drawImage(image.bitmap.asImageBitmap())

                image.overlays.forEach { overlay ->
                    drawContext.canvas.nativeCanvas.apply {
                        save()
                        translate(overlay.position.x, overlay.position.y)
                        rotate(overlay.rotation)
                        drawText(
                            overlay.text,
                            0f,
                            0f,
                            android.graphics.Paint().apply {
                                color = overlay.color.toArgb()
                                textSize = overlay.fontSize
                                isAntiAlias = true
                            }
                        )
                        restore()
                    }
                }
            }

            // Add new text field overlay
            Column(modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)) {
                Row {
                    BasicTextField(
                        value = newText,
                        onValueChange = { newText = it },
                        modifier = Modifier
                            .weight(1f)
                            .background(Color.White, shape = MaterialTheme.shapes.small)
                            .padding(8.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        if (newText.isNotBlank()) {
                            viewModel.addText(newText)
                            newText = ""
                        }
                    }) {
                        Text("Add Text")
                    }
                }
            }
        }
    }
}
