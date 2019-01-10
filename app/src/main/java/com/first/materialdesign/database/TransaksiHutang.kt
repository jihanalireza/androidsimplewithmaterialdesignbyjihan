package com.first.materialdesign.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class TransaksiHutang {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
    @ColumnInfo(name = "kode_hutang")
    var kodeHutang: String? = null
    @ColumnInfo(name = "keterangan_hutang")
    var keteranganHutang: String? = null
    @ColumnInfo(name = "nominal")
    var nominal: Int? = null
    @ColumnInfo(name = "status")
    var status: String? = null
}