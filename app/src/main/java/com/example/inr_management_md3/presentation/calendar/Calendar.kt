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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.inr_management_md3.presentation.theme.INR_Management_Theme
import com.himanshoe.kalendar.ui.Kalendar
import com.himanshoe.kalendar.ui.KalendarType

@Composable
fun CalendarMonthView() {
    BoxWithConstraints(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxSize()
    ) {
//        Kalendar(
//            kalendarStyle = KalendarStyle(
//                kalendarColor = MaterialTheme.colorScheme.surfaceVariant,
//                kalendarSelector = KalendarSelector.Circle(
//                    selectedColor = MaterialTheme.colorScheme.primary,
//                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
//                    defaultColor = Color.Transparent,
//                    defaultTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
//                    eventTextColor = MaterialTheme.colorScheme.primary,
//                    todayColor = MaterialTheme.colorScheme.primary
//                )
//            ),
//            onCurrentDayClick = { day, event ->
//                // handle the date click listener
//            },
//            errorMessage = {
//                // Handle the error if any
//            }
//        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Kalendar(
                    kalendarType = KalendarType.Firey(),
                    kalendarStyle = com.himanshoe.kalendar.common.KalendarStyle(
                        kalendarBackgroundColor = MaterialTheme.colorScheme.surface,
                        kalendarColor = MaterialTheme.colorScheme.primary,
                        kalendarSelector = com.himanshoe.kalendar.common.KalendarSelector.Circle(
                            selectedColor = MaterialTheme.colorScheme.tertiaryContainer,
                            todayColor = MaterialTheme.colorScheme.tertiaryContainer,
                            defaultTextColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
                            defaultColor = Color.Transparent,
                            eventTextColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ),
                    onCurrentDayClick = { day, event ->
                        // handle the date click listener
                    },
                    errorMessage = {
                        // Handle the error if any
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
