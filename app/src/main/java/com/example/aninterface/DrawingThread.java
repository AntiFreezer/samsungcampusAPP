package com.example.aninterface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class DrawingThread implements Runnable{
    private SurfaceHolder surfaceHolder;
    private boolean running;
    private Paint paint;

    public DrawingThread(Context context, SurfaceHolder surfaceHolder){
        this.surfaceHolder = surfaceHolder;
        running = true;

    }

    void stopRunning(){
        running = false;
    }

    @Override
    public void run() {
        while (running){
            Canvas canvas = surfaceHolder.lockCanvas();
            if(canvas != null){
                try{
                    //код жесткий
                }finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
