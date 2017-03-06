package com.example.ryanblaser.tickettoride.GUI.CustomWidgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;
import com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter;

import java.util.List;

import static com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages.GameBoardFragment.BOARD_IMAGE_HEIGHT;
import static com.example.ryanblaser.tickettoride.GUI.Views.SlidingPages.GameBoardFragment.BOARD_IMAGE_WIDTH;

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


            Paint mpaint = new Paint();
            mpaint.setStrokeWidth(10);
            mpaint.setStyle(Paint.Style.STROKE);
            mpaint.setStrokeJoin(Paint.Join.ROUND);
            mpaint.setStrokeCap(Paint.Cap.ROUND);
            mpaint.setDither(true);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) getContext()).getWindowManager()
                    .getDefaultDisplay()
                    .getMetrics(displayMetrics);

            float height = displayMetrics.heightPixels;
            float width = displayMetrics.widthPixels;

            float screenToImageRatioY = height/BOARD_IMAGE_HEIGHT;
            float screenToImageRatioX = width/BOARD_IMAGE_WIDTH;

            List<Route> routes = GameBoardPresenter._SINGLETON.get_AllRoutes();
            for(Route r: routes){

                if(r.get_Color() == 0x733e98){
                    System.out.print(true);
                }
                int pathColor =  r.get_Color();
                mpaint.setColor(pathColor);

                Path path = new Path();
                path.moveTo(screenToImageRatioX*r.getPoint1().x, screenToImageRatioY*r.getPoint1().y);
                path.lineTo(screenToImageRatioX*r.getPoint2().x, screenToImageRatioY*r.getPoint2().y);
                canvas.drawPath(path, mpaint);
                //DashPathEffect effect = new DashPathEffect(new float[] {10,20}, 0);
            }

        }

        public void drawLine(){

        }

}
