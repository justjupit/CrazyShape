package com.jupiter.application.crazyshape

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.jupiter.application.crazyshape.ml.Shapes
import kotlinx.android.synthetic.main.activity_game.view.*
import org.tensorflow.lite.support.image.TensorImage

class HandPaint(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    fun classifyDrawing(bitmap: Bitmap){
        val model = Shapes.newInstance(context)

        val image = TensorImage.fromBitmap(bitmap)

        //val outputs = model.process(image)
        //val probability = outputs.probabilityAsCategoryList

        val outputs = model.process(image).probabilityAsCategoryList.apply {
            sortByDescending { it.score }
        }.take(1)
        var Result:String = ""
        when(outputs[0].label){
            "circle" -> Result = "Circle"
            "square" -> Result = "Square"
            "star" -> Result = "Star"
            "triangle" -> Result = "Triangle"
        }
        Result+=": "+ String.format("%.1f%%",outputs[0].score * 100.0f)

        model.close()
        Toast.makeText(context,Result, Toast.LENGTH_SHORT).show()
    }
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var path: Path = Path()

    init {
        paint.color = Color.WHITE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 60f
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeJoin = Paint.Join.ROUND
    }

    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)
        canvas.drawColor(Color.BLACK)
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val xpos = event.getX()
        val ypos = event.getY()
        when(event.action){
            MotionEvent.ACTION_DOWN -> path.moveTo(xpos,ypos)
            MotionEvent.ACTION_MOVE -> path.lineTo(xpos,ypos)
            MotionEvent.ACTION_UP -> {
                val b = Bitmap.createBitmap(handv.measuredWidth, handv.measuredHeight,
                Bitmap.Config.ARGB_8888)
                val c = Canvas(b)
                handv.draw(c)
                classifyDrawing(b)
            }
        }
        invalidate()
        return true
    }
}


