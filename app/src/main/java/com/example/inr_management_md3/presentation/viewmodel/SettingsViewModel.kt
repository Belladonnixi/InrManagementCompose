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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inr_management_md3.data.datamodels.Medicament
import com.example.inr_management_md3.data.datamodels.TargetRange
import com.example.inr_management_md3.data.repository.InrManagementRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: InrManagementRepository
) : ViewModel() {

    var medicamentList by mutableStateOf(emptyList<Medicament>())

    var medicament by mutableStateOf(Medicament(0, ""))

    private var _selectedMedicament = MutableStateFlow(0)
    val selectedMedicament: StateFlow<Int> get() = _selectedMedicament

    var targetRange by mutableStateOf(TargetRange(0, 0, 0))

    val targetRangeFrom = listOf(
        "",
        "1",
        "2",
        "3",
        "4",
        "5"
    )

    val targetRangeTo = listOf(
        "",
        "1",
        "2",
        "3",
        "4",
        "5"
    )

    var selectedRangeFrom = mutableStateOf(targetRangeFrom[0])
    var selectedRangeTo = mutableStateOf(targetRangeTo[0])

//    fun getMedicaments() {
//        viewModelScope.launch {
//            repository.getAllMedicaments().collect() { response ->
//                medicamentList = response
//            }
//        }
//    }

    fun selectedMedicament(getMedicament: Int) {
        _selectedMedicament.value = getMedicament
    }

    fun addTargetRange(targetRange: TargetRange) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTargetRange(targetRange)
        }
    }
}
