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
