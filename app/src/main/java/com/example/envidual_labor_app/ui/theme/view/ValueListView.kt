package com.example.envidual_labor_app.ui.theme.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.envidual_labor_app.ui.theme.component.ValueCard
import com.example.envidual_labor_app.ui.theme.data.FakeDatabase.valueList
import com.example.envidual_labor_app.ui.theme.data.Hl7InitData
import org.openehealth.ipf.modules.hl7.kotlin.asIterable
import org.openehealth.ipf.modules.hl7.kotlin.get
import org.openehealth.ipf.modules.hl7.kotlin.value
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ca.uhn.hl7v2.HL7Exception
import ca.uhn.hl7v2.model.Structure
import ca.uhn.hl7v2.parser.PipeParser
import com.example.envidual_labor_app.ui.theme.InitDataViewModel
import kotlinx.coroutines.flow.StateFlow
import org.openehealth.ipf.modules.hl7.kotlin.asIterable
import org.openehealth.ipf.modules.hl7.kotlin.get
import org.openehealth.ipf.modules.hl7.kotlin.value
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ValueListView(mylist: InitDataViewModel) {

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberBottomSheetScaffoldState()


    val getData = Hl7InitData()
    getData.initData()
    getData.getPatientName()


    val list = mylist.obxListfilled





    Box(
        Modifier.background(
            color = Color.LightGray
        ),

        contentAlignment = Alignment.Center

    ) {
        LazyColumn {

//            // Iteriere durch alle Segmente
//            for (segment in list.value) {
//                // Überprüfe, ob das aktuelle Segment ein "OBX"-Segment ist
//                if (segment.name == "OBX") {
//
//                    println("Found OBX Segment:")
//                    print(segment[3].value)
//                    segment[3][2].value
//                    ValueCard(valueName = segment[3][2].value, value = , unit = )
//                    // Zugreifen auf die Felder im Segment
//
//                    println("---------------")
//                }
//            }


            item {

            }

            items(list.value.size) {
                // Iteriere durch alle Segmente
                for (segment in list.value) {
                    // Überprüfe, ob das aktuelle Segment ein "OBX"-Segment ist
                    if (segment.name == "OBX") {

                        println("Found OBX Segment:")
                        print(segment[3].value)
                        segment[3][2].value
                        segment[3][2].value?.let { it1 ->
                            ValueCard(
                                valueName = it1,
                                value = 4f,
                                unit = ""
                            )
                        }
                        // Zugreifen auf die Felder im Segment

                        println("---------------")
                    }
                }
//                list.value.listIterator().forEach {
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    ValueCard(segment[3].value, it.value, it.unit)
//
//
////                    ItemDogCard(
////                        it,
////                        onItemClicked = { dog ->
////                            navController.navigate("details/${dog.id}/${dog.name}/${dog.location}")
////                        }
////                    )
//                }
            }

        }
    }


    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetContent = {
            Text(text = "Beispiel")
        }) {

    }


}