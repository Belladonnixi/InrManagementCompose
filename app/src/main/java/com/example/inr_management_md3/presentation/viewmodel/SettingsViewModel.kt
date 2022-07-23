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
import com.example.inr_management_md3.data.datamodels.Medicament
import com.example.inr_management_md3.data.repository.InrManagementRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel(
    private val inrManagementRepository: InrManagementRepository
) : ViewModel() {

    private val preMedicamentList: List<String> = listOf(
        "Please choose",
        "Warfarin",
        "Phenprocoumon",
        "Acenorcoumarol",
        "Other (pills based dosing)",
        "Other (milligram based dosing"
    )

    private val _medicament = MutableStateFlow(emptyList<Medicament>())
    val medicament: StateFlow<List<Medicament>> get() = _medicament
}
