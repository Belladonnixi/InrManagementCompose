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

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.inr_management_md3.R
import com.example.inr_management_md3.presentation.viewmodel.CalendarViewModel
import com.himanshoe.kalendar.common.KalendarKonfig
import com.himanshoe.kalendar.common.KalendarSelector
import com.himanshoe.kalendar.common.KalendarStyle
import com.himanshoe.kalendar.common.data.KalendarEvent
import com.himanshoe.kalendar.ui.Kalendar
import com.himanshoe.kalendar.ui.KalendarType
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 *  Reusable DatePicker Component just DatePickerTextField and DatePickerDialog are needed
 *  for implementation
 */

@Composable
fun DatePickerDialogFirstTry(calendarViewModel: CalendarViewModel) {
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
                dialogState.value = false
                calendarViewModel.setDate("")
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
fun DatePickerTextField(
    modifier: Modifier,
    date: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit,
    label: @Composable (() -> Unit)? = null
) {
    TextField(
        modifier = modifier,
        value = date,
        onValueChange = onValueChange,
        readOnly = true,
        label = label,
        trailingIcon = {
            IconButton(
                onClick = onClick
            ) {
                Icon(Icons.Default.EditCalendar, contentDescription = null)
            }
        }
    )
}

@Composable
fun DatePickerDialog(
    title: String,
    dialogState: MutableState<Boolean>,
    onCurrentDayClicked: (LocalDate, KalendarEvent?) -> Unit,
    errorMessage: (String) -> Unit,
    cancelButton: () -> Unit,
    okButton: () -> Unit
) {
    Dialog(
        onDismissRequest = { dialogState.value = false },
        content = {
            DatePickerDialogContent(
                title = title,
                okButton = okButton,
                cancelButton = cancelButton,
                content = {
                    BodyContent(
                        onCurrentDayClicked = onCurrentDayClicked,
                        errorMessage = errorMessage
                    )
                }
            )
        },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialogContent(
    title: String,
    content: @Composable () -> Unit,
    cancelButton: () -> Unit,
    okButton: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxHeight(0.85f)
            .fillMaxWidth(1f),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(1f)
                .fillMaxWidth(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TitleAndButton(title)
            AddBody(content)
            BottomButtons(
                okButton = okButton,
                cancelButton = cancelButton
            )
        }
    }
}

@Composable
fun BottomButtons(
    cancelButton: () -> Unit,
    okButton: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(20.dp),
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(
            onClick = cancelButton,
            modifier = Modifier
                .width(100.dp)
                .padding(end = 5.dp)
        ) {
            Text(
                text = stringResource(R.string.cancel_text_button),
                fontSize = 16.sp
            )
        }
        TextButton(
            onClick = okButton
        ) {
            Text(
                text = stringResource(R.string.ok_text_button),
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun AddBody(content: @Composable () -> Unit) {
    Box(modifier = Modifier.padding(20.dp)) {
        content()
    }
}

@Composable
fun TitleAndButton(title: String) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}

@Composable
fun BodyContent(
    onCurrentDayClicked: (LocalDate, KalendarEvent?) -> Unit,
    errorMessage: (String) -> Unit
) {
    Kalendar(
        kalendarType = KalendarType.Firey(),
        kalendarKonfig = KalendarKonfig(weekCharacters = 2),
        kalendarStyle = KalendarStyle(
            kalendarBackgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
            kalendarSelector = KalendarSelector.Circle(
                selectedColor = MaterialTheme.colorScheme.primary,
                todayColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                defaultColor = Color.Transparent,
                eventTextColor = MaterialTheme.colorScheme.primaryContainer
            ),
            hasRadius = true,
            shape = RoundedCornerShape(10.dp)
        ),
        onCurrentDayClick = onCurrentDayClicked,
        errorMessage = errorMessage
    )
}
