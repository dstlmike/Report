package com.yourcompany.reportmaker.ui.viewmodel

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourcompany.reportmaker.domain.model.EditableImage
import com.yourcompany.reportmaker.domain.model.TextOverlay
import com.yourcompany.reportmaker.domain.usecase.AddTextOverlayUseCase
import com.yourcompany.reportmaker.domain.usecase.RenderImageWithOverlaysUseCase
import com.yourcompany.reportmaker.domain.usecase.SetOverlaySelectedUseCase
import com.yourcompany.reportmaker.domain.usecase.UpdateOverlayTransformUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor(
    private val addTextOverlayUseCase: AddTextOverlayUseCase,
    private val updateOverlayTransformUseCase: UpdateOverlayTransformUseCase,
    private val setOverlaySelectedUseCase: SetOverlaySelectedUseCase,
    private val renderImageWithOverlaysUseCase: RenderImageWithOverlaysUseCase
) : ViewModel() {

    private val _currentImage = MutableStateFlow<EditableImage?>(null)
    val currentImage: StateFlow<EditableImage?> = _currentImage

    fun setImage(image: EditableImage) {
        _currentImage.value = image
    }

    fun addText(text: String, position: Offset = Offset(100f, 100f)) {
        _currentImage.value?.let {
            addTextOverlayUseCase(it, text, position)
            _currentImage.value = it
        }
    }

    fun selectOverlay(id: String?) {
        _currentImage.value?.let {
            setOverlaySelectedUseCase(it, id)
            _currentImage.value = it
        }
    }

    fun updateOverlayPosition(overlay: TextOverlay, offset: Offset) {
        updateOverlayTransformUseCase.updatePosition(overlay, offset)
        _currentImage.value = _currentImage.value
    }

    fun updateOverlayScale(overlay: TextOverlay, scale: Float) {
        updateOverlayTransformUseCase.updateScale(overlay, scale)
        _currentImage.value = _currentImage.value
    }

    fun updateOverlayRotation(overlay: TextOverlay, rotation: Float) {
        updateOverlayTransformUseCase.updateRotation(overlay, rotation)
        _currentImage.value = _currentImage.value
    }

    fun renderBitmap(): android.graphics.Bitmap? {
        return _currentImage.value?.let {
            renderImageWithOverlaysUseCase(it)
        }
    }
}
