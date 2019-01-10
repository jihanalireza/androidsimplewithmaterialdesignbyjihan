package com.first.materialdesign.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.first.materialdesign.MainActivity
import com.first.materialdesign.R
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.startActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        async(UI){
            bg {
                Thread.sleep(2000)
                startActivity<MainActivity>()
            }.start()
        }

    }
}
