package com.yourcompany.reportmaker.domain.usecase

import androidx.compose.ui.geometry.Offset
import com.yourcompany.reportmaker.domain.model.TextOverlay

class UpdateOverlayTransformUseCase {

    fun updatePosition(overlay: TextOverlay, offset: Offset) {
        overlay.position = offset
    }

    fun updateScale(overlay: TextOverlay, scale: Float) {
        overlay.scale = scale
    }

    fun updateRotation(overlay: TextOverlay, rotation: Float) {
        overlay.rotation = rotation
    }
}
