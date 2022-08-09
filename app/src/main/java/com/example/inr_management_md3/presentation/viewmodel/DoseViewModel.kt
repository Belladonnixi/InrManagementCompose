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
import com.example.inr_management_md3.data.repository.InrManagementRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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
    private var _medicamentDoseList = MutableStateFlow(emptyList<String>())
    val medicamentDoseList: StateFlow<List<String>> get() = _medicamentDoseList

    private var _selectedMedicamentDoseList = MutableStateFlow(emptyList<String>())
    private val selectedMedicamentDoseList: StateFlow<List<String>> get() = _selectedMedicamentDoseList

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
                                        listOf("no medicament type set")
                                    }
                                }
                            }
                    }
                }
            } else {
                _medicamentDoseList.value = listOf("no medicament type set")
            }
        }
    }

    /**
     *  Dose Week functions
     */
    fun addSelectedMedicamentDoseToList(dose: String) {
        _selectedMedicamentDoseList.value += dose
    }

    fun addWeekDosesToDb() {
        Log.e("selected medicament dose list", "${selectedMedicamentDoseList.value}")
    }
}
