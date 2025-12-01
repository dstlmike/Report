package com.yourcompany.reportmaker.domain.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

data class TextOverlay(
    val id: String = java.util.UUID.randomUUID().toString(),
    var text: String,
    var position: Offset = Offset.Zero,
    var rotation: Float = 0f,
    var scale: Float = 1f,
    var fontSize: Float = 32f,
    var color: Color = Color.White,
    var isSelected: Boolean = false
)
