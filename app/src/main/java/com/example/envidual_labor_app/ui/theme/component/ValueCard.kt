package com.example.envidual_labor_app.ui.theme.component

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderPositions
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.uhn.hl7v2.HL7Exception
import ca.uhn.hl7v2.parser.PipeParser
import com.example.envidual_labor_app.ui.theme.data.Hl7InitData
import java.io.File
import java.sql.Ref
import java.time.temporal.ValueRange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValueCard(valueName: String, value: Float, unit: String) {

    var showBottomSheet by remember { mutableStateOf(false) }
    ElevatedCard(
        onClick = {
//            val getData = Hl7InitData()
//            getData.initData()
//            getData.getPatientName()
            showBottomSheet = !showBottomSheet

        },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),

        modifier = Modifier
            .padding(horizontal = 15.dp)


    ) {

        Column {
            Text(

                text = valueName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .padding(horizontal = 15.dp)

            )
            Text(
                text = unit,
                modifier = Modifier
                    .padding(horizontal = 15.dp)

            )
            SliderWithLabel(value, 0f..50f, false)
//                var sliderPosition by remember { mutableStateOf(15f) }
//                Text(text = sliderPosition.toString(), style = MaterialTheme.typography.labelLarge)
//                Slider(
//                    value = sliderPosition,
//                    onValueChange = {  },
//                    colors = SliderDefaults.colors(
//                        thumbColor = MaterialTheme.colorScheme.secondary,
//                        activeTrackColor = MaterialTheme.colorScheme.secondary,
//                        inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
//                    ),
//                    modifier = Modifier
//                        .padding(horizontal = 15.dp),
//                    steps = 3,
//                    valueRange = 0f..50f
//
//                )
        }

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
