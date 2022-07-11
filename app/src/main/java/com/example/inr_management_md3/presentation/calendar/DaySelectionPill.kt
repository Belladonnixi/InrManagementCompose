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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.inr_management_md3.presentation.calendar.model.CalendarState
import com.example.inr_management_md3.presentation.calendar.model.CalendarUiState
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun DaySelectionPill(
    day: DayOfWeek,
    currentDay: LocalDate,
    state: CalendarUiState,
    modifier: Modifier = Modifier,
    widthPerDay: Dp = 48.dp,
    pillColor: Color = MaterialTheme.colorScheme.tertiary
) {
    val widthPerDayPx = with(LocalDensity.current) { widthPerDay.toPx() }
    val cornerRadiusPx = with(LocalDensity.current) { 24.dp.toPx() }

    Canvas(
        modifier = modifier.fillMaxWidth(),
        onDraw = {
            val (offset, size) =
        }
    )
}

private fun getOffsetAndSize(
    width: Float,
    state: CalendarUiState,
    currentWeekStart: LocalDate,
    day: DayOfWeek,
    widthPerDayPx: Float,
    cornerRadiusPx: Float
): Pair<Offset, Float> {
    val dayDelay = state.dayDelay(currentWeekStart)
    val edgePadding = (width - widthPerDayPx * CalendarState.DAYS_IN_WEEK) / 2
}

internal const val DURATION_MILLIS_PER_DAY = 150

private fun normalize(
    x: Float,
    inMin: Float,
    inMax: Float,
    outMin: Float = 0f,
    outMax: Float = 1f
): Float {
    val outRange = outMax - outMin
    val inRange = inMax - inMin
    return (x - inMin) * outRange / inRange + outMin
}