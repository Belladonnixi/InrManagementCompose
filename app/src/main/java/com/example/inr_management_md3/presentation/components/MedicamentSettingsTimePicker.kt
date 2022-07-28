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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import com.example.inr_management_md3.R
import com.example.inr_management_md3.presentation.viewmodel.SettingsViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun MedicamentSettingsTimePicker(settingsViewModel: SettingsViewModel) {
    val dialogState = rememberMaterialDialogState()
    val textState by settingsViewModel.textState.collectAsState()
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
            )
        ) { time ->
            val formattedTime = time.format(
                DateTimeFormatter.ofPattern("hh:mm a")
            )
            settingsViewModel.getFormattedTime(TextFieldValue(formattedTime))
            settingsViewModel.getTime(time)
        }
    }
    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = textState,
            onValueChange = { settingsViewModel.getFormattedTime(it) },
            readOnly = true,
            label = { Text(text = stringResource(R.string.measure_time)) },
            trailingIcon = {
                IconButton(
                    onClick = { dialogState.show() }
                ) {
                    Icon(Icons.Default.Alarm, contentDescription = "Alarm")
                }
            }
        )
    }
}

@Composable
fun MeasureResultTimePicker() {
    val dialogState = rememberMaterialDialogState()
    val textState = remember { mutableStateOf(TextFieldValue()) }
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
            )
        ) { time ->
            val formattedTime = time.format(
                DateTimeFormatter.ofPattern("hh:mm a")
            )
            textState.value = TextFieldValue(formattedTime)
        }
    }
    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = textState.value,
            onValueChange = { textState.value = it },
            readOnly = true,
            label = { Text(text = stringResource(R.string.measure_time)) },
            trailingIcon = {
                IconButton(
                    onClick = { dialogState.show() }
                ) {
                    Icon(Icons.Default.Alarm, contentDescription = "Alarm")
                }
            }
        )
    }
}
