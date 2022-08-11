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
package com.example.inr_management_md3.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inr_management_md3.data.datamodels.InrMeasuringResult
import com.example.inr_management_md3.data.datamodels.Taking
import com.example.inr_management_md3.data.repository.InrManagementRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

/**
 *  ViewModel for HomeScreen logic
 */
class HomeViewModel(
    private val repository: InrManagementRepository
) : ViewModel() {

    /**
     * Initializing for TodayDoseCard, TomorrowDoseNextMeasureCards, StatisticCard
     */
    private val _taking = MutableStateFlow(Taking())
    val taking: StateFlow<Taking> get() = _taking

    private val _dateToday = MutableStateFlow("")
    val dateToday: StateFlow<String> get() = _dateToday

    private val _dateTomorrow = MutableStateFlow("")
    val dateTomorrow: StateFlow<String> get() = _dateTomorrow

    private val _nextMeasureDate = MutableStateFlow("")
    val nextMeasureDate: StateFlow<String> get() = _nextMeasureDate

    private val _lastMeasureDate = MutableStateFlow("")
    val lastMeasureDate: StateFlow<String> get() = _lastMeasureDate

    private val _measureResult = MutableStateFlow(InrMeasuringResult())
    val measureResult: StateFlow<InrMeasuringResult> get() = _measureResult

    /**
     * Initializing for TodayDoseCard, TomorrowDoseNextMeasureCards, StatisticCard
     */
    init {
        viewModelScope.launch(Dispatchers.IO) {
            setTodayDate()
            setTomorrowDate()
            setLastMeasureDate()
            getLastMeasureResult()
        }
    }

    private fun setTodayDate() {
        _dateToday.value = LocalDate.now().format(
            DateTimeFormatter.ofPattern("E, MMM dd. yyyy")
        )
    }

    private fun setTomorrowDate() {
        _dateTomorrow.value = LocalDate.now().plus(1, ChronoUnit.DAYS).format(
            DateTimeFormatter.ofPattern("E, MMM dd. yyyy")
        )
    }

//    private fun setNextMeasureDate() {
//        viewModelScope.launch(Dispatchers.IO) {
//            if (repository.checkIfMeasureAlarmIsSet()) {
//                repository.getLastMeasureAlarm().collect { measureAlarm ->
//                    val measureDate = measureAlarm.everyXDays
//
//                }
//            }
//        }
//    }

    private fun setLastMeasureDate() {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.checkIfMeasureResultExists()) {
                repository.getLastMeasureResult().collect { measureResult ->
                    val date = measureResult.date
                    _lastMeasureDate.value = date!!.format(
                        DateTimeFormatter.ofPattern("E, MMM dd. yyyy")
                    )
                }
            }
        }
    }

    private fun getLastMeasureResult() {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.checkIfMeasureResultExists()) {
                repository.getLastMeasureResult().collect { measureResult ->
                    _measureResult.value.measuringResult = measureResult.measuringResult
                }
            }
        }
    }

    /**
     *  StatisticsCard
     */
}
