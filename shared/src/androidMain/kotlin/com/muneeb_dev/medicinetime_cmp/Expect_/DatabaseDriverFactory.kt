package com.muneeb_dev.medicinetime_cmp.Expect_

import android.content.Context
import com.muneeb_dev.medicinetime_cmp.database.MedicineDatabase

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver


actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(MedicineDatabase.Schema, context, "android_medicine.db")
    }
}



