package com.example.ryanblaser.tickettoride.GUI.CustomWidgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.example.ryanblaser.tickettoride.Client.GameModels.RouteModel.Route;
import com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter;

import java.util.List;

import static com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter.BOARD_IMAGE_HEIGHT;
import static com.example.ryanblaser.tickettoride.GUI.Presenters.GameBoardPresenter.BOARD_IMAGE_WIDTH;


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
                Paint mpaint = new Paint();
                mpaint.setStrokeWidth(10);
                mpaint.setStyle(Paint.Style.STROKE);
                mpaint.setStrokeJoin(Paint.Join.ROUND);
                mpaint.setStrokeCap(Paint.Cap.ROUND);
                mpaint.setDither(true);

                System.out.print(true);

                int pathColor = r.get_Color();

                if(r.get_Owner() == null){

                    if(r.get_Owner() == "Daniel"){
                        int asdf = 1;
                    }
                    float x1 = r.getPoint1().x;
                    float x2 = r.getPoint2().x;
                    float y1 = r.getPoint1().y;
                    float y2 = r.getPoint2().y;

                    Float distance = (float) Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
                    distance /= (r.get_Weight());
                    distance /= 2.0F;
                    DashPathEffect effect = new DashPathEffect(new float[] {distance,distance}, 0);
                    mpaint.setPathEffect(effect);
                }


                mpaint.setColor(pathColor);


                Path path = new Path();
                path.moveTo(screenToImageRatioX * r.getPoint1().x, screenToImageRatioY * r.getPoint1().y);
                path.lineTo(screenToImageRatioX * r.getPoint2().x, screenToImageRatioY * r.getPoint2().y);
                canvas.drawPath(path, mpaint);


            }

        }

        public void drawLine(){

        }

}
