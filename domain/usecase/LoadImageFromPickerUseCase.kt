package com.yourcompany.reportmaker.domain.usecase

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri

class LoadImagesFromPickerUseCase(
    private val resolver: ContentResolver
) {

    operator fun invoke(uris: List<Uri>): List<Bitmap> {
        return uris.map { uri ->
            val source = ImageDecoder.createSource(resolver, uri)
            ImageDecoder.decodeBitmap(source)
        }
    }
}
