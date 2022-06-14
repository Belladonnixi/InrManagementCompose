/** Copyright © 2022 Jessica Ernst */

package com.example.inr_management_md3

import android.app.Application
import com.example.inr_management_md3.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class InrManagementApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@InrManagementApp)
            modules(appModules)
        }
    }
}
