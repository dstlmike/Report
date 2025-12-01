package com.yourcompany.reportmaker.domain.model

sealed class PDFLayoutMode {
    object OneImagePerPage : PDFLayoutMode()
    object GridTwoPerPage : PDFLayoutMode()
    object GridThreePerPage : PDFLayoutMode()
}
