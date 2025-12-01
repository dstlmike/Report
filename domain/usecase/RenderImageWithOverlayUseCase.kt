package com.yourcompany.reportmaker.domain.usecase

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.geometry.Offset
import com.yourcompany.reportmaker.domain.model.EditableImage
import kotlin.math.roundToInt

class RenderImageWithOverlaysUseCase {

    operator fun invoke(image: EditableImage): Bitmap {
        val base = image.bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(base)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        image.overlays.forEach { overlay ->
            paint.color = overlay.color.toArgb()
            paint.textSize = overlay.fontSize * overlay.scale

            canvas.save()
            canvas.rotate(
                overlay.rotation,
                overlay.position.x,
                overlay.position.y
            )

            canvas.drawText(
                overlay.text,
                overlay.position.x,
                overlay.position.y,
                paint
            )

            canvas.restore()
        }

        return base
    }
}
