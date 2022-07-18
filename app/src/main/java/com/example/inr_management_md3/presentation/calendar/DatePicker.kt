/**
 * Copyright © 2022 Jessica Ernst
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries
 * and frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.example.inr_management_md3.presentation.calendar

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.*
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
import com.example.inr_management_md3.presentation.theme.INR_Management_Theme
import com.example.inr_management_md3.presentation.viewmodel.CalendarViewModel
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.common.KalendarStyle
import com.himanshoe.kalendar.ui.Kalendar
import com.himanshoe.kalendar.ui.KalendarType
import org.koin.androidx.compose.inject
import java.time.format.DateTimeFormatter

@Composable
fun DatePickerDialog(
    calendarViewModel: CalendarViewModel
) {
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
                        .padding(top = 16.dp, end = 32.dp, bottom = 16.dp)
                        .border(
                            1.dp,
                            color = MaterialTheme.colorScheme.onSurface,
                            RoundedCornerShape(10.dp)
                        )
                        .background(
                            MaterialTheme.colorScheme.tertiaryContainer,
                            RoundedCornerShape(10.dp)
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .height(500.dp)
                                .wrapContentSize()
                        ) {
                            Kalendar(
                                kalendarType = KalendarType.Firey(),
                                kalendarStyle = KalendarStyle(
                                    kalendarBackgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
                                    kalendarColor = MaterialTheme.colorScheme.primary,
                                    kalendarSelector = KalendarSelector.Circle(
                                        selectedColor = MaterialTheme.colorScheme.tertiaryContainer,
                                        todayColor = MaterialTheme.colorScheme.tertiaryContainer,
                                        defaultTextColor = MaterialTheme.colorScheme.onPrimary,
                                        selectedTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                                        defaultColor = Color.Transparent,
                                        eventTextColor = MaterialTheme.colorScheme.primaryContainer
                                    ),
                                    hasRadius = true,
                                    shape = RoundedCornerShape(10.dp)
                                ),
                                onCurrentDayClick = { day, event ->
                                    val formattedDate =
                                        day.format(DateTimeFormatter.ofPattern("MMM dd. yyyy"))
                                    textState.value = TextFieldValue(formattedDate)
                                },
                                errorMessage = {
                                    // Handle the error if any
                                }
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextButton(
                                onClick = { openPopUp.value = !openPopUp.value }
                            ) {
                                Text(
                                    text = "Cancel",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .padding(start = 32.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.End,
                                verticalArrangement = Arrangement.Center
                            ) {
                                TextButton(
                                    onClick = {
                                        textState.value
                                        openPopUp.value = !openPopUp.value
                                    }
                                ) {
                                    Text(
                                        text = "Save",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                }
                            }
                        }
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
        val calendarViewModel: CalendarViewModel by inject()
        DatePickerDialog(calendarViewModel)
    }
}
