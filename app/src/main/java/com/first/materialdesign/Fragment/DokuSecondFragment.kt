package com.first.materialdesign.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.first.materialdesign.R
import com.first.materialdesign.adapter.MyhutangRecyclerViewAdapter
import com.first.materialdesign.database.AppDatabase
import com.first.materialdesign.database.TransaksiHutang
import kotlinx.android.synthetic.main.fragment_doku_second.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.noButton
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.yesButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DokuSecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_doku_second, container, false)

        getDataHutang()

        return v
    }

    fun getDataHutang(){
        async(UI) {
            val getLunas = bg {
                AppDatabase.getDatabase(getContext()!!.applicationContext).transaksiHutangDao().getLunas()
            }

            showData(getLunas.await())
        }
    }

    fun showData(dataLunas: List<TransaksiHutang>) {
        transLunas.adapter = MyhutangRecyclerViewAdapter(dataLunas,object :MyhutangRecyclerViewAdapter.onClickListener{
            override fun ketikaKlik(item: TransaksiHutang) {
                alert {
                    title = "Konfirmasi Hapus"
                    message = "Apa Anda yakin akan hapus hutang ${item.kodeHutang} tersebut ?"
                    noButton { dialog -> dialog.dismiss() }
                    yesButton { hapusHutang(item.uid) }
                }.show()
            }

            override fun ketikaKlikPanjang(item: TransaksiHutang) {
                alert {
                    title = "LUNAS"
                    message = "LUNAS"
                    yesButton { dialog -> dialog.dismiss() }
                }.show()
            }

        })
        transLunas.layoutManager = LinearLayoutManager(context)
    }
    fun hapusHutang(uid: Int) {
        doAsync {
            bg {
                val hutang = TransaksiHutang()
                hutang.uid = uid

                AppDatabase.getDatabase(context!!.applicationContext).transaksiHutangDao().delete(hutang)
            }
        }

        getDataHutang()
    }



}
