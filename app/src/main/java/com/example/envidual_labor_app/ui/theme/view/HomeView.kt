package com.example.envidual_labor_app.ui.theme.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.envidual_labor_app.ui.theme.viewmodel.InitDataViewModel

@Composable
fun HomeView(mylist: InitDataViewModel) {
    Column {
        TopBarView(mylist)
        AbnormalFindingsView()
        ValueListView(mylist)
    }

}