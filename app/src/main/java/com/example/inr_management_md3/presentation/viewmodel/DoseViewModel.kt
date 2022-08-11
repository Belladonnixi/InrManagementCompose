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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inr_management_md3.data.datamodels.BaseMedicationWeekdays
import com.example.inr_management_md3.data.datamodels.TemporaryMedicationAdjustment
import com.example.inr_management_md3.data.repository.InrManagementRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

/**
 *  SharedViewModel for DoseScreens Logic
 */
const val WARFARIN: Long = 2
const val PHENPROCOUMON: Long = 3
const val ACENOCOUMAROL: Long = 4

class DoseViewModel(
    private val repository: InrManagementRepository
) : ViewModel() {

    /**
     *  Initialize
     */
    private val dosagesPhenprocoumon = listOf(
        "",
        "0",
        "1/4",
        "1/2",
        "3/4",
        "1",
        "1 1/4",
        "1 1/2",
        "1 3/4",
        "2",
        "2 1/4",
        "2 1/2",
        "2 3/4",
        "3"
    )

    private val dosagesAcenocoumarol = listOf(
        "",
        "0",
        "1/4",
        "1/2",
        "3/4",
        "1",
        "1 1/4",
        "1 1/2",
        "1 3/4",
        "2",
        "2 1/4",
        "2 1/2",
        "2 3/4",
        "3"
    )

    private val dosagesWarfarin = listOf(
        "",
        "0",
        "1/2",
        "1",
        "1 1/2",
        "2",
        "2 1/2",
        "3"
    )

    /**
     *  Dose Week
     */
    private val _medicamentDoseList = MutableStateFlow(emptyList<String>())
    val medicamentDoseList: StateFlow<List<String>> get() = _medicamentDoseList

    var selectedMedicamentDoseList = mutableListOf<String>()

    private val _baseMedicationWeekly = MutableStateFlow(BaseMedicationWeekdays())
    private val baseMedicationWeekdays: StateFlow<BaseMedicationWeekdays>
        get() = _baseMedicationWeekly

    /**
     *  TrimDose
     */
    private val _date = MutableStateFlow("")
    val date: StateFlow<String> get() = _date

    private val _realDate = MutableStateFlow<LocalDate?>(null)
    private val realDate: StateFlow<LocalDate?> get() = _realDate

    private val _temporaryMedicationAdjustment = MutableStateFlow(TemporaryMedicationAdjustment())
    private val temporaryMedicationAdjustment: StateFlow<TemporaryMedicationAdjustment>
        get() = _temporaryMedicationAdjustment

    /**
     *  Initialize
     */
    init {
        viewModelScope.launch(Dispatchers.IO) {
            chooseDoseContentExposedDropdowns()
        }
    }

    private fun chooseDoseContentExposedDropdowns() {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.checkIfPatientExists()) {
                repository.getLastPatient().collect { patient ->
                    if (repository.checkIfMedicamentDosageIdExistsInPatient(patient.id_patient)) {
                        repository.getMedicamentDosageWhereIdMatches(patient.medicamentDosageId!!)
                            .collect { medicamentDosage ->
                                _medicamentDoseList.value = when (medicamentDosage.medicament_id) {
                                    WARFARIN -> dosagesWarfarin
                                    PHENPROCOUMON -> dosagesPhenprocoumon
                                    ACENOCOUMAROL -> dosagesAcenocoumarol
                                    else -> {
                                        listOf("", "no medicament type set")
                                    }
                                }
                            }
                    }
                }
            } else {
                _medicamentDoseList.value = listOf("", "no medicament type set")
            }
        }
    }

    /**
     *  Dose Week functions
     */
    fun addSelectedMedicamentDoseToList(dose: String) {
        selectedMedicamentDoseList += dose
    }

    fun addWeekDosesToDb() {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.checkIfTakingExists()) {
                repository.getLastTaking().collect { taking ->
                    repository.getLastPatient().collect { patient ->
                        _baseMedicationWeekly.value.weekdaysDosage = selectedMedicamentDoseList
                        _baseMedicationWeekly.value.patientId = patient.id_patient
                        _baseMedicationWeekly.value.medicamentDosageId =
                            patient.medicamentDosageId!!
                        repository.addBaseMedicationWeekly(baseMedicationWeekdays.value)
                        repository.getLastBaseMedicationWeekdays().collect { bmw ->
                            val bmwId = bmw.idBaseMedicationWeekdays
                            repository.updateTakingBaseMedicationWeekdaysId(
                                bmwId,
                                taking.idTaking
                            )
                        }
                    }
                }
            } else {
                repository.getLastPatient().collect { patient ->
                    _baseMedicationWeekly.value.weekdaysDosage = selectedMedicamentDoseList
                    _baseMedicationWeekly.value.patientId = patient.id_patient
                    _baseMedicationWeekly.value.medicamentDosageId = patient.medicamentDosageId!!
                    repository.addBaseMedicationWeekly(baseMedicationWeekdays.value)
                }
            }
        }
    }

    /**
     *  TrimDose functions
     */
    fun setDate(setDate: String) {
        _date.value = setDate
    }

    fun setRealDate(setRealDate: LocalDate) {
        _realDate.value = setRealDate
    }

    fun addTemporaryMedicationAdjustmentToDb() {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.checkIfMeasureResultExists()) {
                repository.getLastMeasureResult().collect { measureResult ->
                    _temporaryMedicationAdjustment.value.date = realDate.value
                    _temporaryMedicationAdjustment.value.inrMeasuringResultId =
                        measureResult.idInrMeasuringResult
                    _temporaryMedicationAdjustment.value.dosage =
                        selectedMedicamentDoseList[0]
                    repository.addTemporaryMedicationAdjustment(temporaryMedicationAdjustment.value)
                }
            }
        }
    }
}
