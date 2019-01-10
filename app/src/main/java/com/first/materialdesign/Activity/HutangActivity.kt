package com.first.materialdesign.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.first.materialdesign.MainActivity
import com.first.materialdesign.R
import com.first.materialdesign.database.AppDatabase
import com.first.materialdesign.database.TransaksiHutang
import kotlinx.android.synthetic.main.activity_hutang.*
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*

class HutangActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hutang)

        btnTambahHutang.onClick {
            val timestamp = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
            val code = "TH" + timestamp.toString()


            doAsync{
                bg {

                    var hutang = TransaksiHutang()
                    hutang.kodeHutang = code
                    hutang.keteranganHutang = keteranganhutang.text.toString()
                    hutang.nominal = nominalhutang.text.toString().toInt()
                    hutang.status = "hutang"

                    AppDatabase.getDatabase(this@HutangActivity).transaksiHutangDao().insertAll(hutang)
                }
            }

            startActivity<MainActivity>()
        }
    }


}
