package com.example.aninterface.offline;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.Objects;

public class DrawingOffline extends SurfaceView {
    private SurfaceHolder surfaceHolder;
    private DrawingThreadOffline drawingThreadOffline;
    private final PointsOffline points;
    private Path path;

    public DrawingOffline(Context context){
        super(context);
        surfaceHolder = getHolder();
        points = new PointsOffline();
        drawingThreadOffline = new DrawingThreadOffline(surfaceHolder, points);
        init();
    }

    public DrawingOffline(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        points = new PointsOffline();
        init();
    }

    public DrawingThreadOffline getDrawingThread(){
        return drawingThreadOffline;
    }

    public void init(){
        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                drawingThreadOffline = new DrawingThreadOffline(holder, points);
                drawingThreadOffline.start();
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
                //bruh
            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                drawingThreadOffline = new DrawingThreadOffline(holder, points);
                drawingThreadOffline.setStopRequest();
                boolean retry = true;
                while (retry) {
                    try {
                        drawingThreadOffline.join();
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
        if(Objects.equals(drawingThreadOffline.getCurrentShape(), "LINE") || Objects.equals(drawingThreadOffline.getCurrentShape(), "ERASER")){
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path = new Path();
                    path.moveTo(event.getX(), event.getY());
                    switch (drawingThreadOffline.getCurrentShape()){
                        case "LINE":
                            points.addPath(path, drawingThreadOffline.getCurrentColor(), drawingThreadOffline.getCurrentWidth(), drawingThreadOffline.getCurrentFog());
                            break;
                        case "ERASER":
                            points.addPath(path, Color.BLACK, drawingThreadOffline.getCurrentWidth(), false);
                            break;
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if(path != null){
                        path.lineTo(event.getX(), event.getY());
                        invalidate(); // Redrawing
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    path = null; // Reset previous path(current)
                    break;
            }
        }
        else{
            if(MotionEvent.ACTION_DOWN == event.getAction()){
                points.addShape(new float[]{event.getX(), event.getY()}, drawingThreadOffline.getCurrentShape(), drawingThreadOffline.getCurrentColor(), drawingThreadOffline.getCurrentWidth(), drawingThreadOffline.getCurrentFog());
            }
        }
        return true;
    }
}
