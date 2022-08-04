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
import com.example.inr_management_md3.data.datamodels.*
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

    /**
     *  MedicamentSettings
     */
    private val _medicamentTypeList = MutableStateFlow(emptyList<MedicamentType>())
    val medicamentTypeList: StateFlow<List<MedicamentType>> get() = _medicamentTypeList

    private var _selectedMedicamentType = MutableStateFlow(MedicamentType())
    val selectedMedicamentType: StateFlow<MedicamentType> get() = _selectedMedicamentType

    /**
     *  TargetRangeSettings
     */
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

    /**
     *  General (more than one Screen)
     */
    private val _textState = MutableStateFlow(TextFieldValue())
    val textState: StateFlow<TextFieldValue> get() = _textState

    private val _timeState = MutableStateFlow(LocalTime.now())
    private val timeState: StateFlow<LocalTime> get() = _timeState

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

    private var _takingAlarm = MutableStateFlow(TakingAlarm())
    private val takingAlarm: StateFlow<TakingAlarm> get() = _takingAlarm

    /**
     *  MeasureSettings
     */
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

    private val _date = MutableStateFlow("")
    val date: StateFlow<String> get() = _date

    private val _realDate = MutableStateFlow<LocalDate?>(null)
    private val realDate: StateFlow<LocalDate?> get() = _realDate

    private val _measureAlarm = MutableStateFlow(
        MeasureAlarm(
            0,
            0,
            LocalDate.now(),
            LocalTime.now(),
            0
        )
    )
    private val measureAlarm: StateFlow<MeasureAlarm> get() = _measureAlarm

    /**
     *  Initializing
     */
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

    /**
     *  MedicamentSettings
     */
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

    // function to check if patient exists, getting last patientId, getting MedicamentDosageId
    // where the medicament_id is matching the selection of dropdown and updating or creating patient
    fun writeMedicamentDosageToPatientColumn() {
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

    /**
     *  TargetRangeSettings
     */
    fun setTargetRange(setTargetRange: TargetRange) {
        _targetRange.value = setTargetRange
    }

    // if a patient id exists the id will set in Foreign key of TargetRange otherwise it will be null
    fun addTargetRangeToDb() {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.checkIfPatientExists()) {
                repository.getLastPatientId().collect { responseId ->
                    _targetRange.value.patientId = responseId.id_patient
                    _patient.value.id_patient = responseId.id_patient
                    repository.addTargetRange(targetRange.value)
                    repository.getLastTargetRange().collect { response ->
                        _targetRange.value = response
                        _patient.value.targetRangeId = targetRange.value.idTargetRange
                        repository.updatePatientTargetRangeId(
                            patient.value.targetRangeId,
                            patient.value.id_patient
                        )
                    }
                }
            } else {
                repository.addTargetRange(targetRange.value)
                repository.getLastTargetRange().collect { response ->
                    _targetRange.value = response
                    _patient.value.targetRangeId = targetRange.value.idTargetRange
                    repository.addPatient(patient.value)
                    repository.getLastPatientId().collect { responseId ->
                        _targetRange.value.patientId = responseId.id_patient
                        repository.updateTargetRangePatientId(
                            targetRange.value.patientId,
                            targetRange.value.idTargetRange
                        )
                    }
                }
            }
        }
    }

    fun resetTargetRangeDropdowns() {
        selectedRangeFrom = mutableStateOf(targetRangeFrom[0])
        selectedRangeTo = mutableStateOf(targetRangeTo[0])
    }

    /**
     *  MeasureSettings
     */
    fun addMeasureAlarm() {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.checkIfPatientExists()) {
                repository.getLastPatientId().collect { response ->
                    _patient.value.id_patient = response.id_patient
                    _measureAlarm.value.patientId = response.id_patient
                    _measureAlarm.value.measureTime = timeState.value
                    _measureAlarm.value.startDate = realDate.value!!
                    _measureAlarm.value.everyXDays = selectedMeasureDays.value.toInt()
                    repository.addMeasureAlarm(measureAlarm.value)
                    repository.getLastMeasureAlarm().collect { responseId ->
                        _measureAlarm.value.idMeasureAlarm = responseId.idMeasureAlarm
                        repository.updateMeasureAlarmPatientId(
                            measureAlarm.value.patientId,
                            measureAlarm.value.idMeasureAlarm
                        )
                    }
                }
            } else {
                repository.addPatient(patient.value)
                repository.addMeasureAlarm(measureAlarm.value)
                repository.getLastMeasureAlarm().collect { responseId ->
                    _measureAlarm.value.idMeasureAlarm = responseId.idMeasureAlarm
                    repository.updateMeasureAlarmPatientId(
                        measureAlarm.value.patientId,
                        measureAlarm.value.idMeasureAlarm
                    )
                }
            }
        }
    }

    /**
     *  General (more than one Screen)
     */

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

    fun addTakingAlarm() {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.checkIfPatientExists()) {
                repository.getLastPatientId().collect { response ->
                    _patient.value.id_patient = response.id_patient
                    _takingAlarm.value.patientId = response.id_patient
                    _takingAlarm.value.takingTime = timeState.value
                    repository.addTakingAlarm(takingAlarm.value)
                    repository.getLastTakingAlarmId().collect { responseId ->
                        _takingAlarm.value.idTakingTime = responseId.idTakingTime
                        repository.updateTakingAlarmPatientId(
                            takingAlarm.value.patientId,
                            takingAlarm.value.idTakingTime
                        )
                    }
                }
            } else {
                repository.addTakingAlarm(takingAlarm.value)
                repository.getLastTakingAlarmId().collect { responseId ->
                    _takingAlarm.value.idTakingTime = responseId.idTakingTime
                    repository.updateTakingAlarmPatientId(
                        takingAlarm.value.patientId,
                        takingAlarm.value.idTakingTime
                    )
                }
            }
        }
    }
}
