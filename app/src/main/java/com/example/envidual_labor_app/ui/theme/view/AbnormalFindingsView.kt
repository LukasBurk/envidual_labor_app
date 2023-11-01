package com.example.envidual_labor_app.ui.theme.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AbnormalFindingsView() {
    Box(
        Modifier
            .background(
                color = Color.LightGray
            )
            .padding(20.dp),

        contentAlignment = Alignment.Center

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(

                modifier = Modifier.size(24.dp),
                onClick = { }
            ) {
                Icon(
                    Icons.Filled.Warning,
                    "contentDescription",
                    tint = Color.Red,

                    )
            }
            Column {
                Text(
                    text = "19 auffällige Befunde",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Überprüfung notwendig",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
            }


        }

    }
}