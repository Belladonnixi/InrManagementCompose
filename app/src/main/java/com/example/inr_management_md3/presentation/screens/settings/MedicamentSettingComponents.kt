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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.inr_management_md3.R
import com.example.inr_management_md3.presentation.components.TimePickerTextFieldDropdown
import com.example.inr_management_md3.presentation.viewmodel.SettingsViewModel

@Composable
fun MedicamentSettings(settingsViewModel: SettingsViewModel, navController: NavController) {
    var checked by remember { mutableStateOf(false) }

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
                TimePickerTextFieldDropdown()
            }

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = {
                        navController.navigateUp()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 60.dp)
                        .height(60.dp),
                    enabled = true
                ) { Text(stringResource(id = R.string.save)) }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicamentTypeExposedDropdown(settingsViewModel: SettingsViewModel) {
    val options by settingsViewModel.medicamentList.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    val selectedMedicament by settingsViewModel.selectedMedicament.collectAsState()

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
