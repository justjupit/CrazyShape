package com.jupiter.application.crazyshape

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.jupiter.application.crazyshape.ml.Shapes
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_game.view.*
import org.tensorflow.lite.support.image.TensorImage


class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        btnBack.setEnabled(false)

        val intent = getIntent()
        val number:Int = intent.getIntExtra("number",0)
        val type:String? = intent.getStringExtra("type")

        when(number) {
            0 -> txvMsg.text = "Please draw a Circle"
            1 -> txvMsg.text = "Please draw a Square"
            2 -> txvMsg.text = "Please draw a Triangle"
            3 -> txvMsg.text = "Please draw a Star"
        }

        fun classifyDrawing(bitmap: Bitmap){
            val model = Shapes.newInstance(this)

            val image = TensorImage.fromBitmap(bitmap)

            val outputs = model.process(image).probabilityAsCategoryList.apply {
                sortByDescending { it.score }
            }.take(1)
            model.close()

            if (outputs[0].label!=type){
                Toast.makeText(this,"Your drawing is Wrong!", Toast.LENGTH_SHORT).show()
            }
            else if(outputs[0].label==type){
                btnBack.setEnabled(true)
                Toast.makeText(this,"Congratulations! You are Correct.", Toast.LENGTH_SHORT).show()
            }

        }
        handv.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                val xpos = event.getX()
                val ypos = event.getY()
                when(event.action){
                    MotionEvent.ACTION_DOWN -> handv.path.moveTo(xpos,ypos)
                    MotionEvent.ACTION_MOVE -> handv.path.lineTo(xpos,ypos)
                    MotionEvent.ACTION_UP -> {
                        val b = Bitmap.createBitmap(handv.measuredWidth, handv.measuredHeight,
                            Bitmap.Config.ARGB_8888)
                        val c = Canvas(b)
                        handv.draw(c)
                        classifyDrawing(b)

                    }
                }

                handv.invalidate()
                return true
            }

        })

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

