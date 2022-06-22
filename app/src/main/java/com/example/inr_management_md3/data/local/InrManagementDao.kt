/** Copyright © 2022 Jessica Ernst */

package com.example.inr_management_md3.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.inr_management_md3.data.datamodels.Medicament
import kotlinx.coroutines.flow.Flow

@Dao
interface InrManagementDao {

    @Insert(onConflict = REPLACE)
    suspend fun addMedicament(medicament: Medicament)

    @Query("SELECT * FROM medicament")
    fun getAllMedicaments(): Flow<List<Medicament>>
}
