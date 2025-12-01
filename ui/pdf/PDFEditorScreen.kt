package com.yourcompany.reportmaker.ui.pdf

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yourcompany.reportmaker.domain.model.EditableImage
import com.yourcompany.reportmaker.domain.model.PDFLayoutMode
import com.yourcompany.reportmaker.ui.viewmodel.HomeViewModel
import com.yourcompany.reportmaker.ui.viewmodel.PDFViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PDFExportScreen(
    navController: NavController,
    pdfViewModel: PDFViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val images by homeViewModel.images.collectAsState()
    val isExporting by pdfViewModel.isExporting.collectAsState()
    val pdfFile by pdfViewModel.pdfFile.collectAsState()

    var layout by remember { mutableStateOf<PDFLayoutMode>(PDFLayoutMode.OneImagePerPage) }

    Scaffold(topBar = { TopAppBar(title = { Text("Export PDF") }) }) { padding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp)) {

            Text("Select layout:")
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Button(onClick = { layout = PDFLayoutMode.OneImagePerPage }) { Text("1 per page") }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { layout = PDFLayoutMode.GridTwoPerPage }) { Text("2 per page") }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { layout = PDFLayoutMode.GridThreePerPage }) { Text("3 per page") }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { pdfViewModel.exportPDF(images, layout) }) {
                Text("Export PDF")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (isExporting) {
                Text("Exporting...")
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            pdfFile?.let {
                Text("PDF saved: ${it.absolutePath}")
            }
        }
    }
}
