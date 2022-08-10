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
package com.example.inr_management_md3.presentation.screens.dose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.inr_management_md3.R
import com.example.inr_management_md3.data.datamodels.Weekdays
import com.example.inr_management_md3.presentation.components.DatePickerDialog
import com.example.inr_management_md3.presentation.components.DatePickerTextField
import com.example.inr_management_md3.presentation.viewmodel.DoseViewModel
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DosageExposedDropdown(doseViewModel: DoseViewModel) {
    val options by doseViewModel.medicamentDoseList.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedOptionText,
            onValueChange = { selectedOptionText = it },
            readOnly = true,
            label = { Text(text = stringResource(R.string.pills)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier
                .width(120.dp)
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
                        doseViewModel.addSelectedMedicamentDoseToList(selectedOptionText)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun BaseMedicationWeekContent(
    week: List<Weekdays>,
    doseViewModel: DoseViewModel
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.base_medication),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.weekly_format)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .weight(4.5f)
                ) {
                    LazyColumn {
                        itemsIndexed(week) { _, weekdays ->
                            DoseWeekDay(wd = weekdays, doseViewModel)
                        }
                    }
                }
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Button(
                        onClick = { doseViewModel.addWeekDosesToDb() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        enabled = true
                    ) { Text(stringResource(id = R.string.save)) }
                }
            }
        }
    }
}

@Composable
fun DoseWeekDay(
    wd: Weekdays,
    doseViewModel: DoseViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = wd.days))
        Column(
            modifier = Modifier
                .padding(start = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                DosageExposedDropdown(doseViewModel)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.noun_pill),
                    contentDescription = null,
                    modifier = Modifier
                        .size(28.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

// @Composable
// fun BaseMedicationIntervalContent(doseViewModel: DoseViewModel) {
//    Surface(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//            ) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        text = "Base Medication",
//                        style = MaterialTheme.typography.headlineSmall
//                    )
//                }
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        text = "Interval"
//                    )
//                }
//            }
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 16.dp),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Center
//            ) {
//                DosageExposedDropdown(doseViewModel)
//            }
//            FloatingActionButton(
//                onClick = { /*TODO*/ },
//                modifier = Modifier
//                    .padding(32.dp)
//                    .align(Alignment.End)
//            ) {
//                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
//            }
//            BoxWithConstraints(
//                modifier = Modifier
//                    .fillMaxSize(),
//                contentAlignment = Alignment.BottomCenter
//            ) {
//                Button(
//                    onClick = { /* Do something! */ },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(60.dp),
//                    enabled = true
//                ) { Text("Save") }
//            }
//        }
//    }
// }

@Composable
fun TrimDoseContent(doseViewModel: DoseViewModel) {
    val date by doseViewModel.date.collectAsState()
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.temporary_dose_adjustment),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.set_date_and_dose)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                DoseDatePicker(doseViewModel)
            }
            Text(text = stringResource(R.string.set_dose))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                DosageExposedDropdown(doseViewModel)
            }
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = { doseViewModel.addTemporaryMedicationAdjustmentToDb() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    enabled = date.isNotEmpty()
                ) { Text(stringResource(id = R.string.save)) }
            }
        }
    }
}

@Composable
fun DoseDatePicker(doseViewModel: DoseViewModel) {
    val date by doseViewModel.date.collectAsState()
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
                doseViewModel.setDate(formattedDate).toString()
                doseViewModel.setRealDate(day)
            },
            errorMessage = {},
            okButton = { dialogState.value = false },
            cancelButton = {
                doseViewModel.setDate("")
                dialogState.value = false
            }
        )
    }
    DatePickerTextField(
        modifier = Modifier.fillMaxWidth(),
        date = date,
        onValueChange = { doseViewModel.setDate(it) },
        onClick = { dialogState.value = true },
        label = { Text(text = stringResource(R.string.pick_date)) }
    )
}
