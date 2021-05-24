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
                intent = Intent(this@MainActivity,GameActivity::class.java).apply{
                    if (number.text.toString().toInt()==0) {
                        putExtra("type", "circle")
                        putExtra("number", number.text.toString().toInt())
                    }
                    else if (number.text.toString().toInt()==1) {
                        putExtra("type", "square")
                        putExtra("number", number.text.toString().toInt())
                    }
                    else if (number.text.toString().toInt()==2) {
                        putExtra("type", "triangle")
                        putExtra("number", number.text.toString().toInt())
                    }
                    else if (number.text.toString().toInt()==3) {
                        putExtra("type", "star")
                        putExtra("number", number.text.toString().toInt())
                    }
                }
                startActivity(intent)
                recreate()
                return true
            }
        })

    }

    fun generates(){
        val shape = intArrayOf(R.drawable.circle, R.drawable.square, R.drawable.triangle, R.drawable.star)
        val i:Int = (0..3).random()
        number.text = i.toString()

        imgNext.setImageResource(shape[i])
    }
}