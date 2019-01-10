package com.first.materialdesign.database

import android.arch.persistence.room.*

@Dao
interface TransaksiHutangDao {
    @Query("SELECT * FROM transaksihutang")
    fun getAll(): List<TransaksiHutang>

    @Query("SELECT * FROM transaksihutang where status = 'hutang'")
    fun getHutang(): List<TransaksiHutang>

    @Query("SELECT * FROM transaksihutang where status = 'lunas'")
    fun getLunas(): List<TransaksiHutang>

    @Query("SELECT * FROM transaksihutang WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<TransaksiHutang>

//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): TransaksiHutang

    @Insert
    fun insertAll(vararg transaksiHutangs: TransaksiHutang)

    @Update
    fun updateHutang(vararg transaksiHutang: TransaksiHutang)

    @Delete
    fun delete(transaksiHutang: TransaksiHutang)
}