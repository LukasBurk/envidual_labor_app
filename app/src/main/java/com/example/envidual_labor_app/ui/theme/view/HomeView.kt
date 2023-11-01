package com.example.envidual_labor_app.ui.theme.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import ca.uhn.hl7v2.model.Structure
import com.example.envidual_labor_app.ui.theme.InitDataViewModel
import com.example.envidual_labor_app.ui.theme.data.FakeDatabase

@Composable
fun HomeView(mylist: InitDataViewModel) {
    Column {
        TopBarView()
        AbnormalFindingsView()
        ValueListView(mylist)
    }

}