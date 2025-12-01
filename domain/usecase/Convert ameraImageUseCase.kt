package com.yourcompany.reportmaker.domain.usecase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File

class ConvertCameraImageUseCase {

    operator fun invoke(file: File): Bitmap {
        return BitmapFactory.decodeFile(file.absolutePath)
    }
}
