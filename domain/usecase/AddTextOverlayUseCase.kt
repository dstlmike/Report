package com.yourcompany.reportmaker.domain.usecase

import androidx.compose.ui.geometry.Offset
import com.yourcompany.reportmaker.domain.model.EditableImage
import com.yourcompany.reportmaker.domain.model.TextOverlay

class AddTextOverlayUseCase {

    operator fun invoke(
        image: EditableImage,
        text: String,
        position: Offset = Offset(100f, 100f)
    ) {
        val overlay = TextOverlay(
            text = text,
            position = position
        )
        image.overlays.add(overlay)
    }
}
