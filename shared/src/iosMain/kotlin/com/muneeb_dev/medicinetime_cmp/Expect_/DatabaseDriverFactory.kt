package com.muneeb_dev.medicinetime_cmp.Expect_


import com.muneeb_dev.medicinetime_cmp.database.MedicineDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver


actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(MedicineDatabase.Schema, "ios_medicine.db")
    }
}