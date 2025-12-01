package com.yourcompany.reportmaker.ui.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourcompany.reportmaker.domain.model.EditableImage
import com.yourcompany.reportmaker.domain.usecase.LoadImagesFromPickerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadImagesFromPickerUseCase: LoadImagesFromPickerUseCase
) : ViewModel() {

    private val _images = MutableStateFlow<List<EditableImage>>(emptyList())
    val images: StateFlow<List<EditableImage>> = _images

    fun importImages(uris: List<android.net.Uri>) {
        viewModelScope.launch {
            val bitmaps: List<Bitmap> = loadImagesFromPickerUseCase(uris)
            val editableImages = bitmaps.map { bitmap ->
                EditableImage(bitmap = bitmap)
            }
            _images.value = editableImages
        }
    }

    fun addCameraImage(bitmap: Bitmap) {
        val current = _images.value.toMutableList()
        current.add(EditableImage(bitmap = bitmap))
        _images.value = current
    }

    fun removeImage(id: String) {
        _images.value = _images.value.filter { it.id != id }
    }
}
