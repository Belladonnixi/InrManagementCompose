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
import com.example.inr_management_md3.data.datamodels.Medicament
import com.example.inr_management_md3.data.datamodels.TargetRange
import com.example.inr_management_md3.data.repository.InrManagementRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.util.*

/**
 *  Shared ViewModel for MeasureSettings-, TargetRangeSettings- and MedicamentSettings-Screens
 */

private const val TAG = "SettingsViewModel"

class SettingsViewModel(
    private val repository: InrManagementRepository
) : ViewModel() {

    private val _medicamentList = MutableStateFlow(emptyList<Medicament>())
    val medicamentList: StateFlow<List<Medicament>> get() = _medicamentList

    private var _selectedMedicament = MutableStateFlow(Medicament())
    val selectedMedicament: StateFlow<Medicament> get() = _selectedMedicament

//    val timestamp: Date = DateTimeConverters().zonedDateTimeToDate()

    private var _targetRange = MutableStateFlow(
        TargetRange(
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

    init {
        viewModelScope.launch {
            loadData()
        }
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.checkIfTargetRangeExists()) {
                try {
                    getTargetRange()
                } catch (e: Error) {
                    Log.e(TAG, "No data available $e")
                }
                getMedicaments()
            } else {
                getMedicaments()
            }
        }
    }

    private fun getMedicaments() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllMedicaments().collect { response ->
                _medicamentList.value = response
            }
        }
    }

    private fun getTargetRange() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLastTargetRange().collect { response ->
                _targetRange.value = response
            }
        }
    }

    fun selectedMedicament(getMedicament: Medicament) {
        _selectedMedicament.value = getMedicament
    }

    fun addTargetRange(targetRange: TargetRange) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTargetRange(targetRange)
            getTargetRange()
        }
    }

    fun resetTargetRangeDropdowns() {
        selectedRangeFrom = mutableStateOf(targetRangeFrom[0])
        selectedRangeTo = mutableStateOf(targetRangeTo[0])
    }

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
}
