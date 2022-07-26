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
package com.example.inr_management_md3.data.repository

import com.example.inr_management_md3.data.AppDataBase
import com.example.inr_management_md3.data.datamodels.DosageMedicamentType
import com.example.inr_management_md3.data.datamodels.Medicament
import com.example.inr_management_md3.data.datamodels.MedicamentDosage
import com.example.inr_management_md3.data.datamodels.TargetRange
import kotlinx.coroutines.flow.Flow

interface InrManagementRepository {
    suspend fun addMedicament(medicament: Medicament)
    suspend fun addDosageMedicamentType(dosageMedicamentType: DosageMedicamentType)
    suspend fun addTargetRange(targetRange: TargetRange)
    suspend fun addMedicamentDosage(medicamentDosage: MedicamentDosage)
    fun getAllMedicaments(): Flow<List<Medicament>>
    fun getAllDosageMedicamentTypes(): Flow<List<DosageMedicamentType>>
    fun getLastTargetRange(): Flow<TargetRange>
    fun checkIfTargetRangeExists(): Boolean
}

class InrManagementRepositoryImpl(private val appDataBase: AppDataBase) : InrManagementRepository {
    override suspend fun addMedicament(medicament: Medicament) {
        appDataBase.inrManagementDao().addMedicament(medicament)
    }

    override suspend fun addDosageMedicamentType(dosageMedicamentType: DosageMedicamentType) {
        appDataBase.inrManagementDao().addDosageMedicamentType(dosageMedicamentType)
    }

    override suspend fun addTargetRange(targetRange: TargetRange) {
        appDataBase.inrManagementDao().addTargetRange(targetRange)
    }

    override suspend fun addMedicamentDosage(medicamentDosage: MedicamentDosage) {
        appDataBase.inrManagementDao().addMedicamentDosage(medicamentDosage)
    }

    override fun getAllMedicaments(): Flow<List<Medicament>> =
        appDataBase.inrManagementDao().getAllMedicaments()

    override fun getAllDosageMedicamentTypes(): Flow<List<DosageMedicamentType>> =
        appDataBase.inrManagementDao().getAllDosageMedicamentTypes()

    override fun getLastTargetRange(): Flow<TargetRange> =
        appDataBase.inrManagementDao().getLastTargetRange()

    override fun checkIfTargetRangeExists(): Boolean =
        appDataBase.inrManagementDao().checkIfTargetRangeExists(1)
}
