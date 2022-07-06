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

package com.example.inr_management_md3.presentation.navigation

import androidx.compose.runtime.Composable
import com.example.inr_management_md3.R
import com.example.inr_management_md3.data.datamodels.LoadWeekdays
import com.example.inr_management_md3.presentation.screens.dose.BaseMedicationInterval
import com.example.inr_management_md3.presentation.screens.dose.BaseMedicationWeek
import com.example.inr_management_md3.presentation.screens.dose.TrimDose

enum class DoseScreens(
    val icon: Int,
    private val body: @Composable ((DoseScreens) -> Unit) -> Unit
) {
    Week(
        icon = R.drawable.ic_baseline_calendar_view_week_24,
        body = { onScreenChange -> BaseMedicationWeek(onScreenChange, LoadWeekdays.weekdays) }
    ),
    Interval(
        icon = R.drawable.ic_baseline_timelapse_24,
        body = { BaseMedicationInterval() }
    ),
    Dose(
        icon = R.drawable.pill,
        body = { TrimDose() }
    );

    @Composable
    fun content(onScreenChange: (DoseScreens) -> Unit) {
        body(onScreenChange)
    }
}
