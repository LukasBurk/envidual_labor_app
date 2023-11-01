package com.example.envidual_labor_app.ui.theme.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValueCard(valueName: String, value: Float, unit: String) {

    //var showBottomSheet by remember { mutableStateOf(false) }
    ElevatedCard(
        onClick = {
            //showBottomSheet = !showBottomSheet
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

            Box(
                Modifier.padding(20.dp)

            ) {
                SliderWithLabel(value, 0f..15f, false)
            }

        }

    }
}
