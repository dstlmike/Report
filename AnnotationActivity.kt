private fun exportToPdf(bitmap: Bitmap) {
    val pdfPath = File(getExternalFilesDir(null), "report.pdf").absolutePath
    val document = com.itextpdf.text.Document()
    val writer = com.itextpdf.text.pdf.PdfWriter.getInstance(document, FileOutputStream(pdfPath))

    document.open()

    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
    val image = com.itextpdf.text.Image.getInstance(stream.toByteArray())

    image.scaleToFit(500f, 700f)
    document.add(image)

    document.close()

    Toast.makeText(this, "PDF saved at: $pdfPath", Toast.LENGTH_LONG).show()
}

private fun exportToPdf(bitmap: Bitmap) {
    val pdfPath = File(getExternalFilesDir(null), "report.pdf").absolutePath
    val document = com.itextpdf.text.Document()
    val writer = com.itextpdf.text.pdf.PdfWriter.getInstance(document, FileOutputStream(pdfPath))

    document.open()

    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
    val image = com.itextpdf.text.Image.getInstance(stream.toByteArray())

    image.scaleToFit(500f, 700f)
    document.add(image)

    document.close()

    Toast.makeText(this, "PDF saved at: $pdfPath", Toast.LENGTH_LONG).show()
}
