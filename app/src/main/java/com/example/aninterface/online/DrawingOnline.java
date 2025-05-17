package com.example.aninterface.online;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.aninterface.offline.DrawingThreadOffline;

import java.util.Objects;

public class DrawingOnline extends SurfaceView {
    private SurfaceHolder surfaceHolder;
    private DrawingThreadOnline drawingThreadOnline;
    private final PointsOnline points;
    private Path path;

    public DrawingOnline(Context context){
        super(context);
        surfaceHolder = getHolder();
        points = new PointsOnline();
        drawingThreadOnline = new DrawingThreadOnline(surfaceHolder, points);
        init();
    }

    public DrawingOnline(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        points = new PointsOnline();
        init();
    }

    public DrawingThreadOnline getDrawingThread(){
        return drawingThreadOnline;
    }

    public void init(){
        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                drawingThreadOnline = new DrawingThreadOnline(holder, points);
                drawingThreadOnline.start();
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
                //bruh
            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                drawingThreadOnline = new DrawingThreadOnline(holder, points);
                drawingThreadOnline.setStopRequest();
                boolean retry = true;
                while (retry) {
                    try {
                        drawingThreadOnline.join();
                        retry = false;
                    } catch (InterruptedException e) {
                        throw new RuntimeException();
                    }
                }
            }

        });
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(Objects.equals(drawingThreadOnline.getCurrentShape(), "LINE") || Objects.equals(drawingThreadOnline.getCurrentShape(), "ERASER")){
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path = new Path();
                    path.moveTo(event.getX(), event.getY());
                    points.addPath(path, drawingThreadOnline.getCurrentColor(), drawingThreadOnline.getCurrentWidth(), drawingThreadOnline.getCurrentFog(), drawingThreadOnline.getCurrentShape());
                    break;
                case MotionEvent.ACTION_MOVE:
                    if(!points.getPaths().isEmpty()){
                        path = points.getPaths().get(points.getPaths().size() - 1);
                        path.lineTo(event.getX(), event.getY());
                    }
                    break;
            }
        }
        else{
            if(MotionEvent.ACTION_DOWN == event.getAction()){
                points.addPoints(event.getX(), event.getY(), drawingThreadOnline.getCurrentColor(), drawingThreadOnline.getCurrentWidth(), drawingThreadOnline.getCurrentFog(), drawingThreadOnline.getCurrentShape());
            }
        }
        return true;
    }
}
