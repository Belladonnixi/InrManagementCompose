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

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inr_management_md3.data.datamodels.InrMeasuringResult
import com.example.inr_management_md3.data.repository.InrManagementRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

/**
 *  ViewModel for measure result saving to db
 */
class MeasureResultViewModel(
    private val repository: InrManagementRepository
) : ViewModel() {

    private val _date = MutableStateFlow("")
    val date: StateFlow<String> get() = _date

    private val _realDate = MutableStateFlow<LocalDate?>(null)
    private val realDate: StateFlow<LocalDate?> get() = _realDate

    private val _textState = MutableStateFlow(TextFieldValue())
    val textState: StateFlow<TextFieldValue> get() = _textState

    private val _timeState = MutableStateFlow(LocalTime.now())
    private val timeState: StateFlow<LocalTime> get() = _timeState

    private var _time = MutableStateFlow((String()))
    val time: StateFlow<String> get() = _time

    private val _measureResult = MutableStateFlow(InrMeasuringResult())
    private val measureResult: StateFlow<InrMeasuringResult> get() = _measureResult

    val measureResults = listOf(
        "",
        "0",
        "0.1",
        "0.2",
        "0.3",
        "0.4",
        "0.5",
        "0.6",
        "0.7",
        "0.8",
        "0.9",
        "1",
        "1.1",
        "1.2",
        "1.3",
        "1.4",
        "1.5",
        "1.6",
        "1.7",
        "1.8",
        "1.9",
        "2",
        "2.1",
        "2.2",
        "2.3",
        "2.4",
        "2.5",
        "2.6",
        "2.7",
        "2.8",
        "2.9",
        "3",
        "3.1",
        "3.2",
        "3.4",
        "3.5",
        "3.6",
        "3.7",
        "3.8",
        "3.9",
        "4",
        "4.1",
        "4.2",
        "4.3",
        "4.4",
        "4.5",
        "4.6",
        "4.7",
        "4.8",
        "4.9",
        "5"
    )

    var selectedMeasureResult = mutableStateOf(measureResults[0])

    fun setDate(setDate: String) {
        _date.value = setDate
    }

    fun setRealDate(setRealDate: LocalDate) {
        _realDate.value = setRealDate
    }

    fun getTime(time: LocalTime) {
        _timeState.value = time
    }

    fun getFormattedTime(formattedTime: TextFieldValue) {
        _textState.value = formattedTime
    }

    fun addMeasureResultToDb() {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.checkIfPatientExists()) {
                repository.getLastPatient().collect { patient ->
                    _measureResult.value.patientId = patient.id_patient
                    _measureResult.value.measuringResult = selectedMeasureResult.value
                    _measureResult.value.date = realDate.value
                    _measureResult.value.time = timeState.value
                    if (repository.checkIfMeasureAlarmIsSet()) {
                        repository.getLastMeasureAlarm().collect { measureAlarm ->
                            _measureResult.value.measureAlarmId = measureAlarm.idMeasureAlarm
                            repository.addMeasureResult(measureResult.value)
                        }
                        repository.addMeasureResult(measureResult.value)
                    }
                }
            }
        }
    }
}
