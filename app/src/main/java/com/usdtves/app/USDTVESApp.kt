package com.usdtves.app

import android.app.Application
import com.usdtves.app.database.AppDatabase

class USDTVESApp : Application() {
    val database: AppDatabase by lazy { AppDatabase.getInstance(this) }
}
