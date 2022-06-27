package com.example.inr_management_md3.presentation.components

import android.app.TimePickerDialog
import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.inr_management_md3.presentation.theme.INR_Management_Theme
import java.util.*

@Composable
fun TimePickerTextFieldDropdown() {
    var expanded by remember { mutableStateOf(false) }

    val context = LocalContext.current

    // Fetching local context
    val mContext = LocalContext.current

    // Declaring and initializing a calendar
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    // Value for storing time as a string
    val mTime = remember { mutableStateOf("") }

    // Creating a TimePicker dialog
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        { _, mHour: Int, mMinute: Int ->
            mTime.value = "$mHour:$mMinute"
        }, mHour, mMinute, false
    )

    Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.TopStart)) {
        TextField(
            value = "Set notification time",
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "Notification time") },
            trailingIcon = {
                IconButton(
                    onClick = { mTimePickerDialog.show() }
                ) {
                    Icon(Icons.Default.Update, contentDescription = null)
                }
            }
        )
    }
}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mde",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewTimePickerTextFieldDropdown() {
    INR_Management_Theme {
        TimePickerTextFieldDropdown()
    }
}
