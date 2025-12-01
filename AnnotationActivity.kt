class AnnotationActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var editText: EditText
    private lateinit var originalBitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annotation)

        val path = intent.getStringExtra("imagePath")!!
        originalBitmap = BitmapFactory.decodeFile(path)

        imageView = findViewById(R.id.imageView)
        editText = findViewById(R.id.textInput)
        imageView.setImageBitmap(originalBitmap)

        findViewById<Button>(R.id.btnExportPdf).setOnClickListener {
            val finalBitmap = drawTextOnBitmap(originalBitmap, editText.text.toString())
            exportToPdf(finalBitmap)
        }
    }

    private fun drawTextOnBitmap(bitmap: Bitmap, text: String): Bitmap {
        val result = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(result)

        val paint = Paint()
        paint.color = Color.WHITE
        paint.textSize = 64f
        paint.setShadowLayer(5f, 0f, 0f, Color.BLACK)

        canvas.drawText(text, 40f, bitmap.height - 80f, paint)
        return result
    }
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
