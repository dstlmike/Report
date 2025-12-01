package com.yourcompany.reportmaker.domain.usecase

import com.yourcompany.reportmaker.domain.model.EditableImage
import com.yourcompany.reportmaker.domain.model.TextOverlay

class SetOverlaySelectedUseCase {

    operator fun invoke(image: EditableImage, id: String?) {
        image.overlays.forEach {
            it.isSelected = it.id == id
        }
    }
}
