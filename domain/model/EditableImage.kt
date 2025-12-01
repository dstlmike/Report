package com.yourcompany.reportmaker.domain.model

import android.graphics.Bitmap

data class EditableImage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val bitmap: Bitmap,
    val overlays: MutableList<TextOverlay> = mutableListOf()
)
