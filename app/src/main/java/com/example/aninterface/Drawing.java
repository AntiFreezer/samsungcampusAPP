package com.example.aninterface;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class Drawing extends SurfaceView implements SurfaceHolder.Callback {
    //поля + конструктор короче
    private SurfaceHolder surfaceHolder;
    private DrawingThread drawingThread;

    public Drawing(Context context) {
        super(context);
        getHolder().addCallback(this);
        surfaceHolder = getHolder();
    }



    //MAIN FUNCTIONS
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        drawingThread = new DrawingThread(surfaceHolder);
        drawingThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        drawingThread.setStopRequest();
        boolean retry = true;
        while (retry) {
            try {
                drawingThread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }


}
