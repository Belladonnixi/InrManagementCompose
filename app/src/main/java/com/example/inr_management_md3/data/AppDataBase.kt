/** Copyright © 2022 Jessica Ernst */

package com.example.inr_management_md3.data

import androidx.room.RoomDatabase
import com.example.inr_management_md3.data.local.InrManagementDao

abstract class AppDataBase : RoomDatabase() {
    abstract fun inrManagementDao(): InrManagementDao
}
