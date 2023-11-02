package com.example.envidual_labor_app.ui.theme.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.envidual_labor_app.ui.theme.component.ValueCard
import org.openehealth.ipf.modules.hl7.kotlin.get
import org.openehealth.ipf.modules.hl7.kotlin.value
import com.example.envidual_labor_app.ui.theme.viewmodel.InitDataViewModel
import org.openehealth.ipf.modules.hl7.kotlin.value

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ValueListView(mylist: InitDataViewModel) {

    // TODO: BottomSheet Implamation
    //var showBottomSheet by remember { mutableStateOf(false) }
    //val sheetState = rememberBottomSheetScaffoldState()

    val list = mylist.obxListfilled

    Box(
        Modifier.background(
            color = Color.LightGray
        ),
        contentAlignment = Alignment.Center

    ) {
        LazyColumn {
            item {}
            items(list.value.size) {
                // Iteriere durch alle Segmente
                for (segment in list.value) {
                    // Überprüfe, ob das aktuelle Segment ein "OBX"-Segment ist
                    if (segment.name == "OBX") {
                        // Überprüfe, ob es CE ist, bzw. es darf kein ST (Storno) sein!
                        if(segment[2].value == "CE") {
                            Spacer(modifier = Modifier.height(8.dp))
                            ValueCard(valueName = segment[3][2].value.toString(), value = stringToFloat(segment[5].value.toString()), unit = segment[6].value.toString())
                        }
                        //Spacer(modifier = Modifier.height(8.dp))
                        //ValueCard(valueName = segment[3][2].value.toString(), value = stringToFloat(segment[5].value.toString()), unit = segment[6].value.toString())

                        }
                    }
                }
            }

        }
    }


// TODO: BottomSheet Implementation
//    BottomSheetScaffold(
//        scaffoldState = sheetState,
//        sheetContent = {
//            Text(text = "Beispiel")
//        }) {
//
//    }

fun stringToFloat(input: String): Float {
    try {
        return input.toFloat()
    } catch (e: NumberFormatException) {
        // Handle the case where the input string is not a valid float
        // For example, you can return a default value or throw an exception
        // depending on your requirements.
        return 0.0f  // Default value in case of an error
    }
}