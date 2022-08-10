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
package com.example.inr_management_md3.presentation.screens.measure

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
import com.example.inr_management_md3.presentation.viewmodel.MeasureResultViewModel
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeasureResultExposedDropDown(measureResultViewModel: MeasureResultViewModel) {
    val options = measureResultViewModel.measureResults

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by measureResultViewModel.selectedMeasureResult

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedOptionText,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "INR") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .width(110.dp)
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
fun MeasureResultTimePicker(measureResultViewModel: MeasureResultViewModel) {
    val dialogState = rememberMaterialDialogState()
    val timeState by measureResultViewModel.textState.collectAsState()

    TimePickerDialog(
        dialogState = dialogState,
        timeOnClick = { time ->
            val formattedTime = time.format(
                DateTimeFormatter.ofPattern("hh:mm a")
            )
            measureResultViewModel.getFormattedTime(TextFieldValue(formattedTime))
            measureResultViewModel.getTime(time)
        }
    )
    TimePickerTextField(
        modifier = Modifier.fillMaxWidth(),
        time = timeState.text,
        onValueChange = { measureResultViewModel.getFormattedTime(timeState) },
        onClick = { dialogState.show() },
        label = { Text(text = stringResource(R.string.measure_time)) }
    )
}

@Composable
fun MeasureResultDatePicker(measureResultViewModel: MeasureResultViewModel) {
    val date by measureResultViewModel.date.collectAsState()
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
                measureResultViewModel.setDate(formattedDate).toString()
                measureResultViewModel.setRealDate(day)
            },
            errorMessage = {},
            okButton = { dialogState.value = false },
            cancelButton = {
                measureResultViewModel.setDate("")
                dialogState.value = false
            }
        )
    }
    DatePickerTextField(
        modifier = Modifier.fillMaxWidth(),
        date = date,
        onValueChange = { measureResultViewModel.setDate(it) },
        onClick = { dialogState.value = true },
        label = { Text(text = stringResource(R.string.pick_date)) }
    )
}

@Composable
fun MeasureResultContent(measureResultViewModel: MeasureResultViewModel, navController: NavController) {
    val date by measureResultViewModel.date.collectAsState()
    val selectedOptionText by measureResultViewModel.selectedMeasureResult
    val timeState by measureResultViewModel.textState.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Measure Result",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                MeasureResultDatePicker(measureResultViewModel)
            }
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                MeasureResultTimePicker(measureResultViewModel)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                MeasureResultExposedDropDown(measureResultViewModel)
            }
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = {
                        measureResultViewModel.addMeasureResultToDb()
                        navController.navigateUp()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 70.dp)
                        .height(60.dp),
                    enabled = (
                        date.isNotEmpty() &&
                            timeState.text.isNotEmpty() &&
                            selectedOptionText.isNotEmpty()
                        )
                ) { Text("Save") }
            }
        }
    }
}
