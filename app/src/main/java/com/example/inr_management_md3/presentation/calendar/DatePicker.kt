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
package com.example.inr_management_md3.presentation.calendar

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.inr_management_md3.presentation.calendar.model.CalendarState
import com.example.inr_management_md3.presentation.theme.INR_Management_Theme

@Composable
fun DatePickerDialog() {
    val openPopUp = remember { mutableStateOf(false) }
    val textState = remember { mutableStateOf(TextFieldValue()) }

    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = textState.value,
        onValueChange = { textState.value = it },
        readOnly = true,
        label = { Text(text = "Pick date") },
        trailingIcon = {
            IconButton(
                onClick = { openPopUp.value = !openPopUp.value }
            ) {
                Icon(Icons.Default.EditCalendar, contentDescription = null)
            }
        }
    )

    Box {
        val popUpWidth = 380.dp
        val popUpHeight = 600.dp

        if (openPopUp.value) {
            Popup(
                alignment = Alignment.Center,
                properties = PopupProperties()
            ) {
                Box(
                    modifier = Modifier
                        .size(popUpWidth, popUpHeight)
                        .padding(16.dp)
                        .border(1.dp, color = Color.Black, RoundedCornerShape(10.dp))
                ) {
                    Column {
                        Calendar(CalendarState(), onDayClicked = { })
                    }
                }
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mde",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewDatePicker() {
    INR_Management_Theme {
        DatePickerDialog()
    }
}
