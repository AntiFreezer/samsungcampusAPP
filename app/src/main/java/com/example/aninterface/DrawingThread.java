package com.example.aninterface;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.SurfaceHolder;

public class DrawingThread extends Thread{
    //VARIABLES
    private boolean running;
    private final Paint paint;
    private int currentColor;
    private String currentShape;
    private Canvas canvas;
    private int currentWidth;
    private boolean currentFog;
    private final SurfaceHolder surfaceHolder;
    private float x;
    private float y;
    private float xM;
    private float yM;
    Path path;
    public DrawingThread(SurfaceHolder surfaceHolder){
        running = true;
        currentColor = Color.RED;
        currentShape = "LINE";
        currentFog = false;
        this.surfaceHolder = surfaceHolder;
        paint = new Paint();
        x = 0;
        y = 0;
        xM = 1;
        yM = 1;
    }


    //SETTERS
    public void setCurrentColor(int currentColor){
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
    public void setPoints(float x, float y){
        this.x = x;
        this.y = y;
    }
    public void setPointsMove(float xM, float yM){
        this.xM = xM;
        this.yM = yM;
    }
    //GETTERS
    public String getCurrentShape(){
        return this.currentShape;
    }
    public int getCurrentWidth(){
        return this.currentWidth;
    }

    //THREAD
    @Override
    public void run(){
        while (running){
            if(canvas != null){
                try{
                    canvas = surfaceHolder.lockCanvas();
                    //color
                    setCurrentColor(currentColor);
                    paint.setColor(currentColor);
                    //fog
                    if(currentFog){
                        int opacity = 127;
                        paint.setAlpha(opacity);
                    }
                    //width
                    paint.setStrokeWidth(getCurrentWidth());
                    //shape
                    switch (getCurrentShape()){
                        case "LINE":
                            path.moveTo(x, y);
                            path.lineTo(xM, yM);
                            break;
                        case "SQUARE":
                            canvas.drawLine(x, y, x + getCurrentWidth(), y, paint);
                            canvas.drawLine(x + getCurrentWidth(), y, x + getCurrentWidth(), y - getCurrentWidth(), paint);
                            canvas.drawLine(x + getCurrentWidth(), y - getCurrentWidth(), x, y - getCurrentWidth(), paint);
                            canvas.drawLine(x, y - getCurrentWidth(), x, y, paint);
                            break;
                        case "CIRCLE":
                            canvas.drawCircle(x, y, getCurrentWidth(), paint);
                            break;
                    }
                }finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
