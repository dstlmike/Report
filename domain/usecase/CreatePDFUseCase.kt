package com.yourcompany.reportmaker.domain.usecase

import android.content.Context
import android.graphics.Bitmap
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.PageSize
import com.itextpdf.text.pdf.PdfWriter
import com.yourcompany.reportmaker.domain.model.PDFLayoutMode
import java.io.File
import java.io.FileOutputStream
import java.util.*

class CreatePDFUseCase(
    private val context: Context
) {

    operator fun invoke(
        images: List<Bitmap>,
        layoutMode: PDFLayoutMode
    ): File {

        val file = File(
            context.getExternalFilesDir(null),
            "report-${UUID.randomUUID()}.pdf"
        )

        val document = Document(PageSize.A4)
        PdfWriter.getInstance(document, FileOutputStream(file))
        document.open()

        when (layoutMode) {
            is PDFLayoutMode.OneImagePerPage ->
                images.forEach { addSingleImage(document, it) }

            is PDFLayoutMode.GridTwoPerPage ->
                addGridPages(document, images, 2)

            is PDFLayoutMode.GridThreePerPage ->
                addGridPages(document, images, 3)
        }

        document.close()
        return file
    }

    private fun addSingleImage(document: Document, bitmap: Bitmap) {
        val stream = java.io.ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val img = Image.getInstance(stream.toByteArray())

        img.scaleToFit(PageSize.A4.width - 40f, PageSize.A4.height - 40f)
        img.alignment = Image.ALIGN_CENTER

        document.add(img)
        document.newPage()
    }

    private fun addGridPages(document: Document, images: List<Bitmap>, perPage: Int) {
        val chunks = images.chunked(perPage)

        chunks.forEach { group ->
            group.forEach { bitmap ->
                val stream = java.io.ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                val img = Image.getInstance(stream.toByteArray())

                img.scaleToFit(PageSize.A4.width / 2, PageSize.A4.height / 2)
                img.alignment = Image.ALIGN_CENTER

                document.add(img)
            }
            document.newPage()
        }
    }
}
