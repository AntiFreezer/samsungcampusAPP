package com.example.aninterface;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class Drawing extends SurfaceView {
    SurfaceHolder surfaceHolder;
    private DrawingThread drawingThread;

    public Drawing(Context context){
        super(context);
        surfaceHolder = getHolder();
        drawingThread = new DrawingThread(surfaceHolder);
        init();
    }

    public Drawing(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        init();
    }
    public DrawingThread getDrawingThread(){
        return drawingThread;
    }

    public void init(){
        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                drawingThread = new DrawingThread(holder);
                drawingThread.start();
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
                //bruh
            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                drawingThread = new DrawingThread(holder);
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
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawingThread.setPoints(event.getX(), event.getY());
            case MotionEvent.ACTION_MOVE:
                drawingThread.setPointsMove(event.getX(), event.getY());
        }
        invalidate();
        return true;
    }
}
