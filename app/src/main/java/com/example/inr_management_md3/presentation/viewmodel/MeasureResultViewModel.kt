package com.example.inr_management_md3.presentation.viewmodel

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.example.inr_management_md3.data.repository.InrManagementRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.time.LocalTime

/**
 *  ViewModel for everything around measure result
 */
class MeasureResultViewModel(
    private val repository: InrManagementRepository
) : ViewModel() {

    private val _date = MutableStateFlow("")
    val date: StateFlow<String> get() = _date

    private val _realDate = MutableStateFlow<LocalDate?>(null)
    private val realDate: StateFlow<LocalDate?> get() = _realDate

    private val _textState = MutableStateFlow(TextFieldValue())
    val textState: StateFlow<TextFieldValue> get() = _textState

    private val _timeState = MutableStateFlow(LocalTime.now())
    private val timeState: StateFlow<LocalTime> get() = _timeState

    private var _time = MutableStateFlow((String()))
    val time: StateFlow<String> get() = _time
}
