/**
 * Copyright © 2022 Jessica Ernst
 *
 * This project and source code may use libraries or frameworks that are released under various
 * Open-Source licenses. Use of those libraries and frameworks are governed by their own individual
 * licenses.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.example.inr_management_md3.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import com.example.inr_management_md3.R
import com.example.inr_management_md3.presentation.viewmodel.SettingsViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 *  Reusable TimePicker components
 */

@Composable
fun TimePickerTextField(
    modifier: Modifier,
    time: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit,
    label: @Composable (() -> Unit)? = null
) {
    TextField(
        modifier = modifier,
        value = time,
        onValueChange = onValueChange,
        readOnly = true,
        label = label,
        trailingIcon = {
            IconButton(
                onClick = onClick
            ) {
                Icon(Icons.Default.Alarm, contentDescription = "Alarm")
            }
        }
    )
}

@Composable
fun TimePickerDialog(
    dialogState: MaterialDialogState,
    timeOnClick: (LocalTime) -> Unit
) {
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton(
                stringResource(R.string.ok),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.primary)
            )
            negativeButton(
                stringResource(R.string.cancel),
                textStyle = TextStyle(color = MaterialTheme.colorScheme.primary)
            )
        },
        backgroundColor = MaterialTheme.colorScheme.tertiaryContainer
    ) {
        timepicker(
            initialTime = LocalTime.now(),
            colors = TimePickerDefaults.colors(
                selectorColor = MaterialTheme.colorScheme.primary,
                activeBackgroundColor = MaterialTheme.colorScheme.primary,
                inactiveBackgroundColor = Color.White,
                headerTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                inactiveTextColor = Color.Black
            ),
            onTimeChange = timeOnClick
        )
    }
}

@Composable
fun MedicamentSettingsTimePicker(settingsViewModel: SettingsViewModel) {
    val dialogState = rememberMaterialDialogState()
    val timeState by settingsViewModel.textState.collectAsState()

    TimePickerDialog(
        dialogState = dialogState,
        timeOnClick = { time ->
            val formattedTime = time.format(
                DateTimeFormatter.ofPattern("hh:mm a")
            )
            settingsViewModel.getFormattedTime(TextFieldValue(formattedTime))
            settingsViewModel.getTime(time)
        }
    )
    TimePickerTextField(
        modifier = Modifier.fillMaxWidth(),
        time = timeState.text,
        onValueChange = { settingsViewModel.getFormattedTime(timeState) },
        onClick = { dialogState.show() },
        label = { Text(text = stringResource(R.string.measure_time)) }
    )
}

@Composable
fun MeasureResultTimePicker() {
    val dialogState = rememberMaterialDialogState()
    val timeState = remember { mutableStateOf(TextFieldValue()) }

    TimePickerDialog(
        dialogState = dialogState,
        timeOnClick = { time ->
            val formattedTime = time.format(
                DateTimeFormatter.ofPattern("hh:mm a")
            )
            timeState.value = TextFieldValue(formattedTime)
        }
    )
    TimePickerTextField(
        modifier = Modifier.fillMaxWidth(),
        time = timeState.value.text,
        onValueChange = { timeState.value = TextFieldValue(it) },
        onClick = { dialogState.show() },
        label = { Text(text = stringResource(R.string.measure_time)) }
    )
}
