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

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inr_management_md3.presentation.theme.INR_Management_Theme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.TimePickerDefaults
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun TimePickerTextFieldDropdown() {
    val dialogState = rememberMaterialDialogState()
    val textState = remember { mutableStateOf(TextFieldValue()) }
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok", textStyle = TextStyle(color = MaterialTheme.colorScheme.primary))
            negativeButton(
                "Cancel",
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
            label = { Text(text = "Set notification time") },
            trailingIcon = {
                IconButton(
                    onClick = { dialogState.show() }
                ) {
                    Icon(Icons.Default.Alarm, contentDescription = null)
                }
            }
        )
    }
}

@Composable
fun MeasureResultTimePickerTextFieldDropdown() {
    val dialogState = rememberMaterialDialogState()
    val textState = remember { mutableStateOf(TextFieldValue()) }
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok", textStyle = TextStyle(color = MaterialTheme.colorScheme.primary))
            negativeButton(
                "Cancel",
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
            label = { Text(text = "Set measure time") },
            trailingIcon = {
                IconButton(
                    onClick = { dialogState.show() }
                ) {
                    Icon(Icons.Default.Alarm, contentDescription = null)
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
