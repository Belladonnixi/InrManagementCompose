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
package com.example.inr_management_md3.presentation.screens.calendar

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.inr_management_md3.presentation.navigation.Screens
import com.example.inr_management_md3.presentation.theme.INR_Management_Theme
import com.example.inr_management_md3.presentation.viewmodel.CalendarViewModel
import com.himanshoe.kalendar.ui.Kalendar
import com.himanshoe.kalendar.ui.KalendarType
import java.time.format.DateTimeFormatter

const val TAG = "Calendar"

@Composable
fun CalendarMonthView(calendarViewModel: CalendarViewModel, navController: NavController) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.tertiaryContainer,
                        RoundedCornerShape(10.dp)
                    )
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Kalendar(
                    kalendarType = KalendarType.Firey(),
                    kalendarStyle = com.himanshoe.kalendar.common.KalendarStyle(
                        kalendarBackgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
                        kalendarColor = MaterialTheme.colorScheme.primary,
                        kalendarSelector = com.himanshoe.kalendar.common.KalendarSelector.Circle(
                            selectedColor = Color.White,
                            todayColor = Color.White,
                            defaultTextColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = Color.Black,
                            defaultColor = Color.Transparent,
                            eventTextColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        hasRadius = true,
                        shape = RoundedCornerShape(10.dp),
                        elevation = 10.dp
                    ),
                    onCurrentDayClick = { day, _ ->
                        val formattedDate =
                            day.format(DateTimeFormatter.ofPattern("MMM dd. yyyy"))
                        calendarViewModel.setDate(formattedDate).toString()
                        calendarViewModel.setRealDate(day)
                        navController.navigate(Screens.CalendarDay.route)
                    },
                    errorMessage = { exception ->
                        Log.e(TAG, "Error with CalendarMonthView $exception")
                    }
                )
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
fun PreviewCalendar() {
    INR_Management_Theme {
    }
}
