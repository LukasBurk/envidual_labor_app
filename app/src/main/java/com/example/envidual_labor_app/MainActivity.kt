package com.example.envidual_labor_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.envidual_labor_app.ui.theme.InitDataViewModel
import com.example.envidual_labor_app.ui.theme.data.Hl7Database
import com.example.envidual_labor_app.ui.theme.data.Hl7InitData
import com.example.envidual_labor_app.ui.theme.view.HomeView
import kotlinx.coroutines.launch
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import com.example.envidual_labor_app.ui.theme.M3BottomSheetTheme

class MainActivity : ComponentActivity() {
    val mylist: InitDataViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //val getData = Hl7InitData()
            //getData.initData()
            //getData.getPatientName()
            mylist.initData()
            HomeView(mylist)

        }
    }
}

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    //val store = createStore(appReducer, AppState())
//
//                    //log the initial state
//
//                    //logger.info(store.state)
//
//
//
//                }
//                var counter by remember { mutableStateOf(0) }
//
//                //var counter by remember { mutableStateOf(0) }
//
//                //store.getState().counter by remember { mutableStateOf(0) }
//                Column(
//                    modifier = Modifier.fillMaxSize(),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.SpaceAround
//                ) {
//
//                    Text(text = counter.toString(), style = MaterialTheme.typography.displayLarge)
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceAround
//                    ) {
//                        Button(
//                            onClick = {
//                                println("Steigerung")
//                                Hl7Database()
//                                //store.subscribe;
//                                //unsubscribe
//
//                                // println("Steigerung")
//                                counter++
//                            },
//                            modifier = Modifier.width(150.dp),
//                            content = {
//                                Text(
//                                    text = "-",
//                                    style = MaterialTheme.typography.displayMedium
//                                )
//                            })
//                        Button(
//                            onClick = {
//                                //store.subscribe
//                                println("Decrement")
//                                Hl7Database()
//                                counter--
//                            },
//                            modifier = Modifier.width(150.dp),
//                            content = {
//                                Text(
//                                    text = "+",
//                                    style = MaterialTheme.typography.displayMedium
//                                )
//                            })
//
//                }
//            }
//        }
//    }
//}
