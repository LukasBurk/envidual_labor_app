//package com.example.envidual_labor_app.ui.theme.component
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.KeyboardArrowLeft
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.envidual_labor_app.ui.theme.model.Value
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun TopBar(dogList: List<Value>) {
//
//
//    Column {
//
//
//        Box(
//            Modifier.background(
//                color = Color.Blue
//            ),
//
//            contentAlignment = Alignment.Center
//
//        ) {
//            Column {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                ) {
//                    Column(modifier = Modifier.padding(30.dp)) {
//                        IconButton(
//
//                            modifier = Modifier.size(24.dp),
//                            onClick = { }
//                        ) {
//                            Icon(
//                                Icons.Filled.KeyboardArrowLeft,
//                                "contentDescription",
//                                tint = Color.White,
//
//                                )
//                        }
//                        Spacer(modifier = Modifier.height(20.dp))
//                        Text(
//                            text = "Test Jeannette HL7",
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.Bold,
//                            textAlign = TextAlign.Start,
//                            color = Color.White
//                        )
//
//                        Spacer(modifier = Modifier.height(8.dp))
//
//                        Text(
//                            text = "Test Subject",
//                            textAlign = TextAlign.Start,
//                            color = Color.White
//                        )
//                    }
//                }
//                Row(modifier = Modifier.padding(8.dp)) {
//                    Column(modifier = Modifier.padding(16.dp)) {
//                        Text(
//                            text = "Geburtstagsdatum",
//                            textAlign = TextAlign.Start,
//                            color = Color.White
//                        )
//
//                        Spacer(modifier = Modifier.height(8.dp))
//
//                        Text(
//                            text = "1.1.1980",
//                            fontSize = 18.sp,
//                            textAlign = TextAlign.Start,
//                            color = Color.White
//                        )
//                    }
//
//                    Column(modifier = Modifier.padding(16.dp)) {
//                        Text(
//                            text = "Tagebuchnummer",
//                            textAlign = TextAlign.Start,
//                            color = Color.White
//                        )
//
//                        Spacer(modifier = Modifier.height(8.dp))
//
//                        Text(
//                            text = "0555890287",
//                            fontSize = 18.sp,
//                            textAlign = TextAlign.Start,
//                            color = Color.White
//                        )
//
//                    }
//                }
//            }
//        }
//
//        Box(
//            Modifier.background(
//                color = Color.LightGray
//            ),
//
//            contentAlignment = Alignment.Center
//
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//            ) {
//                IconButton(
//
//                    modifier = Modifier.size(24.dp),
//                    onClick = { }
//                ) {
//                    Icon(
//                        Icons.Filled.KeyboardArrowLeft,
//                        "contentDescription",
//                        tint = Color.White,
//
//                        )
//                }
//
//            }
//
//        }
//
//        Box(
//            Modifier.background(
//                color = Color.LightGray
//            ),
//
//            contentAlignment = Alignment.Center
//
//        ) {
//            LazyColumn {
//
//
//                item {
//
//                }
//
//                items(dogList.size) {
//                    dogList.forEach {
//                        Spacer(modifier = Modifier.height(8.dp))
//                        ValueCard(it.name, it.value, it.unit)
//
//
////                    ItemDogCard(
////                        it,
////                        onItemClicked = { dog ->
////                            navController.navigate("details/${dog.id}/${dog.name}/${dog.location}")
////                        }
////                    )
//                    }
//                }
//
//            }
//        }
//
////        Column {
////            Spacer(modifier = Modifier.height(8.dp))
////            ValueCard()
////        }
//    }
////    Row(
////        modifier = Modifier
////            .fillMaxWidth()
////    ) {
////        Column(modifier = Modifier.padding(16.dp)) {
////            IconButton(
////                modifier = Modifier.size(24.dp),
////                onClick = { }
////            ) {
////                Icon(
////                    Icons.Filled.KeyboardArrowLeft,
////                    "contentDescription",
////                )
////            }
////            Text(
////                text = "Test Jeannette HL7",
////                textAlign = TextAlign.Start,
////            )
////
////            Spacer(modifier = Modifier.height(8.dp))
////
////            Text(
////                text = "Test Subject",
////                textAlign = TextAlign.Start,
////            )
////        }
////    }
////    Row(modifier = Modifier.padding(80.dp)) {
////        Column(modifier = Modifier.padding(16.dp)) {
////            Text(
////                text = "Geburtstagsdatum",
////                textAlign = TextAlign.Start,
////            )
////
////            Spacer(modifier = Modifier.height(8.dp))
////
////            Text(
////                text = "1.1.1980",
////                textAlign = TextAlign.Start,
////            )
////        }
////
////        Column(modifier = Modifier.padding(16.dp)) {
////            Text(
////                text = "Tagebuchnummer",
////                textAlign = TextAlign.Start,
////            )
////
////            Spacer(modifier = Modifier.height(8.dp))
////
////            Text(
////                text = "0555890287",
////                textAlign = TextAlign.Start,
////            )
////
////        }
////    }
////    Column {
////        Spacer(modifier = Modifier.height(90.dp))
////        ValueCard()
////    }
//
//
//}
