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

<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.camera.view.PreviewView
        android:id="@+id/previewView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

    <Button
        android:id="@+id/btnCapture"
        android:text="Capture"
        android:layout_gravity="bottom|center"
        android:layout_margin="24dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
</FrameLayout>
