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

        generates()

        val img: ImageView = findViewById(R.id.imgTitle)
        GlideApp.with(this)
            .load(R.drawable.cover)
            .override(800,600)
            .into(img)

        Toast.makeText(this,"Author = 陳義德",Toast.LENGTH_SHORT).show()

        imgNext.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                generates()

            }
        })

        imgNext.setOnLongClickListener(object: View.OnLongClickListener{
            override fun onLongClick(p0: View): Boolean {
                startActivity(intent)
                return true
            }
        })

    }

    fun generates(){
        val shape = intArrayOf(R.drawable.circle, R.drawable.square, R.drawable.triangle, R.drawable.star)
        val i:Int = (0..3).random()
        val img2: ImageView = findViewById(R.id.imgNext)
        if(i==0){
            GlideApp.with(this)
                .load(shape[i])
                .override(800,600)
                .into(img2)
        }
        else if(i==1){
            GlideApp.with(this)
                .load(shape[i])
                .override(800,600)
                .into(img2)
        }
        else if(i==2){
            GlideApp.with(this)
                .load(shape[i])
                .override(800,600)
                .into(img2)
        }
        else if(i==3){
            GlideApp.with(this)
                .load(shape[i])
                .override(800,600)
                .into(img2)
        }
        intent = Intent(this@MainActivity,GameActivity::class.java).apply{
            putExtra("number", i)
            if (i==0) {
                putExtra("type", "circle")
            }
            else if (i==1) {
                putExtra("type", "square")
            }
            else if (i==2) {
                putExtra("type", "triangle")
            }
            else if (i==3) {
                putExtra("type", "star")
            }
        }
    }
}