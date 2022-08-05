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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.inr_management_md3.R
import com.example.inr_management_md3.presentation.components.TimePickerDialog
import com.example.inr_management_md3.presentation.components.TimePickerTextField
import com.example.inr_management_md3.presentation.viewmodel.SettingsViewModel
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.format.DateTimeFormatter

@Composable
fun MedicamentSettingsContent(settingsViewModel: SettingsViewModel, navController: NavController) {
    var checked by remember { mutableStateOf(false) }
    val selectedMedicament by settingsViewModel.selectedMedicamentType.collectAsState()
    val name by settingsViewModel.savedMedicamentType.collectAsState()
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, bottom = 30.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                if (name.isNotEmpty()) {
                    SavedSettingsBox(settingsViewModel)
                }
            }

            MedicamentTypeExposedDropdown(settingsViewModel)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.enable_alarm))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) {
                    Switch(
                        checked = checked,
                        onCheckedChange = { checked = it }
                    )
                }
            }
            if (checked) {
                MedicamentSettingsTimePicker(settingsViewModel)
            }

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = {
                        if (checked) {
                            settingsViewModel.writeMedicamentDosageToPatientColumn()
                            settingsViewModel.addTakingAlarm()
                            navController.navigateUp()
                        } else {
                            settingsViewModel.writeMedicamentDosageToPatientColumn()
                            navController.navigateUp()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 60.dp)
                        .height(60.dp),
                    enabled = selectedMedicament.type.isNotEmpty()
                ) { Text(stringResource(id = R.string.save)) }
            }
        }
    }
}

@Composable
fun SavedSettingsBox(settingsViewModel: SettingsViewModel) {
    val name by settingsViewModel.savedMedicamentType.collectAsState()
    val time by settingsViewModel.time.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Your saved medicament settings:",
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.SemiBold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Selected Medicament: " +
                        name
                )
            }
            if (time.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Your Taking Time:",
                        modifier = Modifier.padding(16.dp)
                    )
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = time,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicamentTypeExposedDropdown(settingsViewModel: SettingsViewModel) {
    val options by settingsViewModel.medicamentTypeList.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    val selectedMedicament by settingsViewModel.selectedMedicamentType.collectAsState()

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedMedicament.type,
            onValueChange = {},
            readOnly = true,
            label = { Text(stringResource(R.string.choose_medicament_type)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(text = selectionOption.type) },
                    onClick = {
                        settingsViewModel.selectedMedicament(selectionOption)
                        expanded = false
                    }
                )
            }
        }
    }
}
