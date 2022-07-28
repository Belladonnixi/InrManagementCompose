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
import androidx.compose.ui.unit.dp
import com.example.inr_management_md3.R
import com.example.inr_management_md3.presentation.components.DatePickerDialog
import com.example.inr_management_md3.presentation.components.DatePickerTextField
import com.example.inr_management_md3.presentation.components.MeasureResultTimePicker
import com.example.inr_management_md3.presentation.viewmodel.CalendarViewModel
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeasureResultExposedDropDown() {
    val options = listOf(
        "0",
        "0.1",
        "0.2",
        "0.3",
        "0.4",
        "0.5",
        "0.6",
        "0.7",
        "0.8",
        "0.9",
        "1",
        "1.1",
        "1.2",
        "1.3",
        "1.4",
        "1.5",
        "1.6",
        "1.7",
        "1.8",
        "1.9",
        "2",
        "2.1",
        "2.2",
        "2.3",
        "2.4",
        "2.5",
        "2.6",
        "2.7",
        "2.8",
        "2.9",
        "3",
        "3.1",
        "3.2",
        "3.4",
        "3.5",
        "3.6",
        "3.7",
        "3.8",
        "3.9",
        "4",
        "4.1",
        "4.2",
        "4.3",
        "4.4",
        "4.5",
        "4.6",
        "4.7",
        "4.8",
        "4.9",
        "5"
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

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
fun MeasureResultDatePicker(calendarViewModel: CalendarViewModel) {
    val date by calendarViewModel.date.collectAsState()
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
                calendarViewModel.setDate(formattedDate).toString()
                calendarViewModel.setRealDate(day)
            },
            errorMessage = {},
            okButton = { dialogState.value = false },
            cancelButton = {
                calendarViewModel.setDate("")
                dialogState.value = false
            }
        )
    }
    DatePickerTextField(
        modifier = Modifier.fillMaxWidth(),
        date = date,
        onValueChange = { calendarViewModel.setDate(it) },
        onClick = { dialogState.value = true },
        label = { Text(text = stringResource(R.string.pick_date)) }
    )
}

@Composable
fun MeasureResultContent(calendarViewModel: CalendarViewModel) {
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
                MeasureResultDatePicker(calendarViewModel)
            }
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                MeasureResultTimePicker()
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                MeasureResultExposedDropDown()
            }
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = { /* Do something! */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 65.dp)
                        .height(60.dp),
                    enabled = true
                ) { Text("Save") }
            }
        }
    }
}
