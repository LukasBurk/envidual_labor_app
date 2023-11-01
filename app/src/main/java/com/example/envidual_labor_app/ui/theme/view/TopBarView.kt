package com.example.envidual_labor_app.ui.theme.view


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.envidual_labor_app.R
import com.example.envidual_labor_app.ui.theme.data.Hl7InitData
import com.example.envidual_labor_app.ui.theme.model.Value

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopBarView() {

    val getData = Hl7InitData()
    getData.initData()


    Box(
        Modifier.background(
            color = colorResource(R.color.blue_700)
        ),

        contentAlignment = Alignment.Center

    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(30.dp)) {
                    IconButton(

                        modifier = Modifier.size(24.dp),
                        onClick = { }
                    ) {
                        Icon(
                            Icons.Filled.KeyboardArrowLeft,
                            "contentDescription",
                            tint = Color.White,


                            )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = getData.getPatientName(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Test Subject",
                        textAlign = TextAlign.Start,
                        color = Color.White
                    )
                }
            }
            Row(modifier = Modifier.padding(8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Geburtstagsdatum",
                        textAlign = TextAlign.Start,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = getData.getPatientBirthOfDate(),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start,
                        color = Color.White
                    )
                }

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Tagebuchnummer",
                        textAlign = TextAlign.Start,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = getData.getPatientTagebuchnummer(),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start,
                        color = Color.White
                    )

                }
            }
        }
    }

}
