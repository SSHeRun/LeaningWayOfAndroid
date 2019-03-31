package com.example.customviewdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {


    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context,AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(30);
        //paint.setStyle(Paint.Style.STROKE);
        //paint.setStyle(Paint.Style.FILL);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        float radius = getWidth()>getHeight()?getWidth()/2:getHeight()/2;
        radius = radius - 15;
        canvas.drawCircle(getWidth()/2,getWidth()/2,radius,paint);
    }
}
