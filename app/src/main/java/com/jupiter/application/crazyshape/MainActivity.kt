package com.jupiter.application.crazyshape

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import kotlinx.android.synthetic.main.activity_main.*

@GlideModule
public final class MyAppGlideModule : AppGlideModule()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val img: ImageView = findViewById(R.id.imgTitle)
        GlideApp.with(this)
            .load(R.drawable.cover)
            .override(800,600)
            .into(img)

        Toast.makeText(this,"Author = 陳義德",Toast.LENGTH_LONG).show()
        imgNext.setOnLongClickListener(object: View.OnLongClickListener{
            override fun onLongClick(p0: View): Boolean {
                intent = Intent(this@MainActivity,GameActivity::class.java)
                startActivity(intent)
                return true
            }
        })
    }
}