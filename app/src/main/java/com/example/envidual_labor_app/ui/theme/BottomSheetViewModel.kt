package com.example.envidual_labor_app.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

class BottomSheetViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val showBottomSheet: StateFlow<Boolean> = savedStateHandle.getStateFlow("showBottomSheet", false)



}