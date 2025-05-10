package com.example.aninterface.offline;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Drawing extends SurfaceView {
    SurfaceHolder surfaceHolder;
    private DrawingThread drawingThread;
    Points points;
    Path path;

    public Drawing(Context context){
        super(context);
        surfaceHolder = getHolder();
        points = new Points();
        drawingThread = new DrawingThread(surfaceHolder, points);
        init();
    }

    public Drawing(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        points = new Points();
        init();
    }
    public DrawingThread getDrawingThread(){
        return drawingThread;
    }

    public void init(){
        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                drawingThread = new DrawingThread(holder, points);
                drawingThread.start();
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
                //bruh
            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                drawingThread = new DrawingThread(holder, points);
                drawingThread.setStopRequest();
                boolean retry = true;
                while (retry) {
                    try {
                        drawingThread.join();
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
        if(Objects.equals(drawingThread.getCurrentShape(), "LINE")){
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path = new Path();
                    path.moveTo(event.getX(), event.getY());
                    points.addPath(path);
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
                points.addPoints(event.getX(), event.getY());
            }
        }
        return true;
    }
}
