package com.example.inr_management_md3.util.domain

import androidx.compose.ui.text.input.TextFieldValue
import java.time.LocalTime

/**
 *  class for making TimePicker Composable stateless and data usable for all ViewModels where
 *  they are needed
 */

data class TimePickerTimeState(
    val time: LocalTime,
    val formattedTime: String,
    val textState: TextFieldValue
)
