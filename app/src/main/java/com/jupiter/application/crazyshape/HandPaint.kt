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


}


