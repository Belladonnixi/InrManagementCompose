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
package com.example.inr_management_md3.di

import androidx.room.Room
import com.example.inr_management_md3.data.AppDataBase
import com.example.inr_management_md3.data.repository.InrManagementRepository
import com.example.inr_management_md3.data.repository.InrManagementRepositoryImpl
import com.example.inr_management_md3.presentation.viewmodel.CalendarViewModel
import com.example.inr_management_md3.presentation.viewmodel.DoseViewModel
import com.example.inr_management_md3.presentation.viewmodel.SettingsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.annotation.KoinReflectAPI
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDataBase::class.java,
            "InrManagement.db"
        )
            .createFromAsset("inr_management.db")
            .build()
    }
}

val repositoryModule = module {
    single<InrManagementRepository> { InrManagementRepositoryImpl(get()) }
}

@OptIn(KoinReflectAPI::class)
val viewModelModule = module {
    viewModel { CalendarViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { DoseViewModel() }
}

val appModules = listOf(
    dataModule,
    repositoryModule,
    viewModelModule
)
