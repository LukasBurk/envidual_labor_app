package com.example.envidual_labor_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.envidual_labor_app.ui.theme.viewmodel.InitDataViewModel
import com.example.envidual_labor_app.ui.theme.view.HomeView

class MainActivity : ComponentActivity() {
    val mylist: InitDataViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mylist.initData()
            HomeView(mylist)

        }
    }
}