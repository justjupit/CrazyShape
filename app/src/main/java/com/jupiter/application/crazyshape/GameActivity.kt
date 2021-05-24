package com.jupiter.application.crazyshape

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jupiter.application.crazyshape.ml.Shapes
import kotlinx.android.synthetic.main.activity_game.*
import org.tensorflow.lite.support.image.TensorImage


class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val intent = getIntent()
        val number:Int = intent.getIntExtra("shapenumber",0)

        if (number==0){
            txvMsg.text= "Please draw a Circle"
        }
        else if (number==1){
            txvMsg.text= "Please draw a Square"
        }
        else if (number==2){
            txvMsg.text= "Please draw a Triangle"
        }
       else if (number==3){
            txvMsg.text= "Please draw a Star"
        }


        btnBack.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?){
                finish()
            }
        })

        btnClear.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                handv.path.reset()
                handv.invalidate()
            }
        })
    }
}
