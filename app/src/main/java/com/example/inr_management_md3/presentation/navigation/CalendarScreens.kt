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
package com.example.inr_management_md3.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.inr_management_md3.presentation.calendar.ListViewCalendar
import com.example.inr_management_md3.presentation.calendar.MonthViewCalendar
import com.example.inr_management_md3.presentation.viewmodel.CalendarViewModel

/**
 * Calendar screens navigation for TabBar to keep the Composable testable
 */
enum class CalendarScreens(
    val icon: ImageVector,
    private val body: @Composable ((CalendarScreens) -> Unit) -> Unit
) {
    Month(
        icon = Icons.Filled.CalendarMonth,
        body = {
            MonthViewCalendar(CalendarViewModel())
        }
    ),
    List(
        icon = Icons.Filled.List,
        body = {
            ListViewCalendar(
                calendarViewModel = CalendarViewModel()
            )
        }
    );

    @Composable
    fun content(onScreenChange: (CalendarScreens) -> Unit) {
        body(onScreenChange)
    }
}
