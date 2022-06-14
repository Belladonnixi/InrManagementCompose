/** Copyright © 2022 Jessica Ernst */

package com.example.inr_management_md3.di

import androidx.room.Room
import com.example.inr_management_md3.data.AppDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDataBase::class.java,
            "InrManagement.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val repositoryModule = module { }

val viewModelModule = module { }

val appModules = listOf(
    dataModule,
    repositoryModule,
    viewModelModule
)
