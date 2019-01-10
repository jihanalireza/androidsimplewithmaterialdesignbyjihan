package com.first.materialdesign

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.first.materialdesign.Activity.DokuActivity
import com.first.materialdesign.Activity.GowesActivity
import com.first.materialdesign.Activity.HutangActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dokuMenu.onClick {
            startActivity<DokuActivity>("page" to "Tab Pager")
        }

        gowesMenu.onClick {
            startActivity<GowesActivity>("page" to "Gowes")
        }

        transaksiMenu.onClick {
            startActivity<DokuActivity>("page" to "Transaction")
        }

        postBtn.onClick {
            startActivity<HutangActivity>()
        }

        cameraBtn.onClick {
            dispatchTakePictureIntent()
        }

        galleryBtn.onClick {
            dispatchGalleryIntent()
        }

    }

    val REQUEST_IMAGE_CAPTURE = 1

    val SELECTED_IMAGE_GALLERY = 2

    private fun dispatchGalleryIntent() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"Select image"),SELECTED_IMAGE_GALLERY)
    }


    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            gambarSelfie.setImageBitmap(imageBitmap)
        }

        if (requestCode == SELECTED_IMAGE_GALLERY && resultCode == RESULT_OK) {
            val imageUri= data!!.data
            gambarSelfie.setImageURI(imageUri)
        }
    }


}
