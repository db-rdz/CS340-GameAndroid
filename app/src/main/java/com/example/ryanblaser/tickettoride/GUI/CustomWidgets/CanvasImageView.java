package com.example.ryanblaser.tickettoride.GUI.CustomWidgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by benjamin on 3/03/17.
 */

public class CanvasImageView extends ImageView {


        public CanvasImageView(Context context) {
            super(context);
            // TODO Auto-generated constructor stub
        }

        public CanvasImageView(Context context, AttributeSet attrs)
        {
            super(context, attrs);
            // TODO Auto-generated constructor stub
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint p;
            p = new Paint();
            p.setDither(true);
            p.setColor(0xFF00CC00);  // alpha.r.g.b
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeJoin(Paint.Join.ROUND);
            p.setStrokeCap(Paint.Cap.ROUND);
            p.setStrokeWidth(20);

            Path mPath = new Path();
            mPath.moveTo(0, 0);  //Origin
            mPath.lineTo(300,400); //Destination
            p.setPathEffect(new DashPathEffect(new float[] {50,50}, 0));
            canvas.drawPath(mPath, p);

            PointF asdf = new PointF(10,10);



        }

        public void drawLine(){

        }

}
