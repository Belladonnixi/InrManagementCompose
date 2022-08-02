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
package com.example.inr_management_md3.presentation.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.inr_management_md3.R
import com.example.inr_management_md3.presentation.components.DatePickerDialog
import com.example.inr_management_md3.presentation.components.DatePickerTextField
import com.example.inr_management_md3.presentation.components.TimePickerDialog
import com.example.inr_management_md3.presentation.components.TimePickerTextField
import com.example.inr_management_md3.presentation.viewmodel.SettingsViewModel
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeasureTimeRangeExposedDropdownTo(settingsViewModel: SettingsViewModel) {
    val options = settingsViewModel.measureDays
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by settingsViewModel.selectedMeasureDays

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedOptionText,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = stringResource(R.string.every)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.width(115.dp)
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(text = selectionOption) },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun MeasureSettingsDatePicker(settingsViewModel: SettingsViewModel) {
    val date by settingsViewModel.date.collectAsState()
    // Dialog state Manager
    val dialogState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    if (dialogState.value) {
        DatePickerDialog(
            title = stringResource(R.string.pick_date_title),
            dialogState = dialogState,
            onCurrentDayClicked = { day, _ ->
                val formattedDate =
                    day.format(DateTimeFormatter.ofPattern("MMM dd. yyyy"))
                settingsViewModel.setDate(formattedDate).toString()
                settingsViewModel.setRealDate(day)
            },
            errorMessage = {},
            okButton = {
                dialogState.value = false
            },
            cancelButton = {
                settingsViewModel.setDate("")
                dialogState.value = false
            }
        )
    }
    DatePickerTextField(
        modifier = Modifier.fillMaxWidth(),
        date = date,
        onValueChange = { settingsViewModel.setDate(it) },
        onClick = { dialogState.value = true },
        label = { Text(text = stringResource(R.string.pick_date)) }
    )
}

@Composable
fun MeasureSettingsTimePicker(settingsViewModel: SettingsViewModel) {
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
fun MeasureSettingsContent(
    settingsViewModel: SettingsViewModel,
    navController: NavController
) {
    val date by settingsViewModel.date.collectAsState()
    val options = settingsViewModel.measureDays
    val selectedOptionText by settingsViewModel.selectedMeasureDays
    val timeState by settingsViewModel.textState.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, bottom = 30.dp)
        ) {
            Text(text = stringResource(R.string.measure_interval))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                MeasureTimeRangeExposedDropdownTo(settingsViewModel)
                Column(
                    modifier = Modifier.padding(start = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = stringResource(R.string.days))
                }
            }
            Text(text = stringResource(R.string.start_date))
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                MeasureSettingsDatePicker(settingsViewModel)
            }
            Text(text = stringResource(id = R.string.alarm_time))
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                MeasureSettingsTimePicker(settingsViewModel)
            }
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = { navController.navigateUp() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 60.dp)
                        .height(60.dp),
                    enabled =
                    date.isNotEmpty() && options.isNotEmpty() && selectedOptionText.isNotEmpty() &&
                        timeState.text.isNotEmpty()
                ) { Text(stringResource(id = R.string.save)) }
            }
        }
    }
}
