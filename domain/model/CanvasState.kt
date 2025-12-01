package com.yourcompany.reportmaker.domain.model

import androidx.compose.ui.geometry.Offset

data class CanvasState(
    val zoom: Float = 1f,
    val rotation: Float = 0f,
    val translation: Offset = Offset.Zero,
    val isPanning: Boolean = false
)
