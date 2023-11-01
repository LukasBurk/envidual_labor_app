package com.example.envidual_labor_app.ui.theme.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheetSample() {

    var showBottomSheet by remember { mutableStateOf(false) }
    Button(onClick = { showBottomSheet = !showBottomSheet }) {
        Text(text = "Show Bottom")
    }
    val sheetState = rememberBottomSheetScaffoldState()
    if (showBottomSheet) {
        BottomSheetScaffold(
            scaffoldState = sheetState,
            sheetContent = {
                Text(text = "Beispiel")
            }) {

        }

    }

}


