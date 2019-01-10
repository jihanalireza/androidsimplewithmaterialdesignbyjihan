package com.first.materialdesign.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.first.materialdesign.R


import com.first.materialdesign.database.TransaksiHutang

import kotlinx.android.synthetic.main.hutang_list.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onLongClick

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyhutangRecyclerViewAdapter(
    private val mValues: List<TransaksiHutang>,
    val klikgroup: onClickListener
) : RecyclerView.Adapter<MyhutangRecyclerViewAdapter.ViewHolder>() {

//    private val mOnClickListener: View.OnClickListener
//
//    init {
//        mOnClickListener = View.OnClickListener { v ->
//            val item = v.tag as DummyItem
//            // Notify the active callbacks interface (the activity, if the fragment is attached to
//            // one) that an item has been selected.
//            mListener?.onListFragmentInteraction(item)
//        }
//    }

    interface onClickListener {
        fun ketikaKlik(item: TransaksiHutang)
        fun ketikaKlikPanjang(item: TransaksiHutang)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hutang_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.kode.text = item.kodeHutang
        holder.keterangan.text = item.keteranganHutang
        holder.nominal.text = item.nominal.toString()

        if (item.status == "hutang"){
            holder.sts.setTextColor(Color.parseColor("#f24242"))
            holder.sts.text = "Hutang"
        }else{
            holder.sts.setTextColor(Color.parseColor("#05d71e"))
            holder.sts.text = "Lunas"
        }

        holder.listItem.onClick {
            klikgroup.ketikaKlik(item)
        }

        holder.listItem.onLongClick {
            klikgroup.ketikaKlikPanjang(item)
        }


    }


    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val kode: TextView = mView.kodeHutang
        val keterangan: TextView = mView.keteranganHutang
        val nominal: TextView = mView.nominalHutang
        val sts: TextView = mView.statusHutang
        val listItem: CardView = mView.itemTransaksi

        override fun toString(): String {
            return super.toString()
        }
    }
}
