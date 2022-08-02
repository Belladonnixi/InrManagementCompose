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

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inr_management_md3.data.datamodels.MedicamentType
import com.example.inr_management_md3.data.datamodels.Patient
import com.example.inr_management_md3.data.datamodels.TargetRange
import com.example.inr_management_md3.data.repository.InrManagementRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

/**
 *  Shared ViewModel for MeasureSettings-, TargetRangeSettings- and MedicamentSettings-Screens
 */

private const val TAG = "SettingsViewModel"

class SettingsViewModel(
    private val repository: InrManagementRepository
) : ViewModel() {

    private val _medicamentTypeList = MutableStateFlow(emptyList<MedicamentType>())
    val medicamentTypeList: StateFlow<List<MedicamentType>> get() = _medicamentTypeList

    private var _selectedMedicamentType = MutableStateFlow(MedicamentType())
    val selectedMedicamentType: StateFlow<MedicamentType> get() = _selectedMedicamentType

//    val timestamp: Date = DateTimeConverters().zonedDateTimeToDate()

    private var _targetRange = MutableStateFlow(
        TargetRange(
            0,
            0,
            0,
            0
        )
    )
    val targetRange: StateFlow<TargetRange> get() = _targetRange

    val targetRangeFrom = listOf("", "1", "2", "3", "4", "5")

    val targetRangeTo = listOf("", "1", "2", "3", "4", "5")

    var selectedRangeFrom = mutableStateOf(targetRangeFrom[0])
    var selectedRangeTo = mutableStateOf(targetRangeTo[0])

    private val _textState = MutableStateFlow(TextFieldValue())
    val textState: StateFlow<TextFieldValue> get() = _textState

    private val _timeState = MutableStateFlow(LocalTime.now())
    val timeState: StateFlow<LocalTime> get() = _timeState

    private val _date = MutableStateFlow("")
    val date: StateFlow<String> get() = _date

    private val _realDate = MutableStateFlow<LocalDate?>(null)
    val realDate: StateFlow<LocalDate?> get() = _realDate

    val measureDays = listOf(
        "",
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9",
        "10",
        "11",
        "12",
        "13",
        "14",
        "15",
        "16",
        "17",
        "18",
        "19",
        "20",
        "21",
        "22",
        "23",
        "24",
        "25",
        "26",
        "27",
        "28",
        "29",
        "30"
    )

    var selectedMeasureDays = mutableStateOf(measureDays[0])

    private val _patient = MutableStateFlow(
        Patient(
            0,
            null,
            null,
            null,
            null,
            null
        )
    )
    val patient: StateFlow<Patient> get() = _patient

    init {
        viewModelScope.launch {
            loadData()
        }
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.checkIfTargetRangeExists()) {
                try {
                    getTargetRangeFromDb()
                } catch (e: Error) {
                    Log.e(TAG, "No data available $e")
                }
                getMedicamentsFromDb()
            } else {
                getMedicamentsFromDb()
            }
        }
    }

    private fun getMedicamentsFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllMedicaments().collect { response ->
                _medicamentTypeList.value = response
            }
        }
    }

    private fun getTargetRangeFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLastTargetRange().collect { response ->
                _targetRange.value = response
            }
        }
    }

    fun selectedMedicament(getMedicamentType: MedicamentType) {
        _selectedMedicamentType.value = getMedicamentType
    }

    // if a patient id exists the id will set in Foreign key of TargetRange otherwise it will be null
    fun addTargetRangeToDb(targetRange: TargetRange) {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.checkIfPatientExists()) {
                repository.getLastPatientId().collect { response ->
                    targetRange.patientId = response.id_patient
                    repository.addTargetRange(targetRange)
                    getTargetRangeFromDb()
                }
            } else {
                repository.addTargetRange(targetRange)
                getTargetRangeFromDb()
            }
        }
    }

    fun resetTargetRangeDropdowns() {
        selectedRangeFrom = mutableStateOf(targetRangeFrom[0])
        selectedRangeTo = mutableStateOf(targetRangeTo[0])
    }

    // getting LocalTime for Converting it to save it in db
    fun getTime(time: LocalTime) {
        _timeState.value = time
    }

    fun getFormattedTime(formattedTime: TextFieldValue) {
        _textState.value = formattedTime
    }

    fun resetTextState() {
        val text = TextFieldValue("")
        _textState.value = text
    }

    fun setDate(setDate: String) {
        _date.value = setDate
    }

    fun setRealDate(setRealDate: LocalDate) {
        _realDate.value = setRealDate
    }

    fun writeMedicamentDosageSelectionToPatient() {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.checkIfPatientExists()) {
                repository.getLastPatientId().collect { responseId ->
                    _patient.value.id_patient = responseId.id_patient
                    repository.getMedicamentDosageId(
                        selectedMedicamentType.value.idMedicamentType
                    ).collect { response ->
                        _patient.value.medicamentDosageId = response.id_medicament_dosage
                        repository.updatePatientMedicamentDosageId(
                            patient.value.medicamentDosageId,
                            patient.value.id_patient
                        )
                    }
                }
            } else {
                repository.getMedicamentDosageId(
                    selectedMedicamentType.value.idMedicamentType
                ).collect { response ->
                    _patient.value.medicamentDosageId = response.id_medicament_dosage
                    repository.addPatient(patient.value)
                }
            }
        }
    }
}
