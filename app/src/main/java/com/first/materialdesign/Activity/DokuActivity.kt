package com.first.materialdesign.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.first.materialdesign.Fragment.DokuFirstFragment
import com.first.materialdesign.Fragment.DokuSecondFragment
import com.first.materialdesign.R
import kotlinx.android.synthetic.main.activity_doku.*

import org.jetbrains.anko.toast

class DokuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doku)


        val page = intent.getStringExtra("page")
        toast(page)


        setViewPager()


    }

    fun setViewPager(){
        val adapter = TabAdapter(supportFragmentManager)
        pager.adapter = adapter
        tabLayout.setupWithViewPager(pager)
    }

    class TabAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(i: Int): Fragment? {
            var fragment: Fragment? = null
            when (i) {
                0 -> fragment = DokuFirstFragment()
                1 -> fragment = DokuSecondFragment()
            }
            return fragment
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {

            when (position) {
                0 -> return "Hutang"
                1 -> return "Lunas"
            }

            return super.getPageTitle(position)
        }
    }
}
