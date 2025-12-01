package com.yourcompany.reportmaker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yourcompany.reportmaker.domain.model.EditableImage
import com.yourcompany.reportmaker.domain.model.PDFLayoutMode
import com.yourcompany.reportmaker.domain.usecase.CreatePDFUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class PDFViewModel @Inject constructor(
    private val createPDFUseCase: CreatePDFUseCase
) : ViewModel() {

    private val _pdfFile = MutableStateFlow<File?>(null)
    val pdfFile: StateFlow<File?> = _pdfFile

    private val _isExporting = MutableStateFlow(false)
    val isExporting: StateFlow<Boolean> = _isExporting

    fun exportPDF(images: List<EditableImage>, layout: PDFLayoutMode) {
        viewModelScope.launch {
            _isExporting.value = true

            val bitmaps = images.mapNotNull { it.bitmap }
            val file = createPDFUseCase(bitmaps, layout)
            _pdfFile.value = file

            _isExporting.value = false
        }
    }
}
