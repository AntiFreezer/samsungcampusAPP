package com.example.aninterface;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class DrawingThread extends Thread{
    //VARIABLES
    private boolean running;
    Paint paint;
    private int currentColor;
    String currentShape;
    private Canvas canvas;
    int currentWidth;
    boolean currentFog;
    private SurfaceHolder surfaceHolder;
    public DrawingThread(SurfaceHolder surfaceHolder){
        running = true;
        currentColor = Color.RED;
        currentShape = "Линия";
        currentFog = false;
        this.surfaceHolder = surfaceHolder;
    }

    //SETTERS
    public void setCurrentColor(String color){
        this.currentColor = currentColor;
    }
    public void setCurrentShape(String currentShape) {
        this.currentShape = currentShape;
    }
    public void setCurrentWidth(int currentWidth){
        this.currentWidth = currentWidth;
    }
    public void setCurrentFog(boolean currentFog){
        this.currentFog = currentFog;
    }
    public void setStopRequest(){
        running = false;
    }

    //THREAD
    @Override
    public void run(){
        while (running){
            canvas = surfaceHolder.lockCanvas();
            if(canvas != null){
                try{
                    paint = new Paint(Color.RED);
                    paint.setColor(Color.RED);
                    canvas.drawCircle(10, 20, 1000, paint);
                }finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
