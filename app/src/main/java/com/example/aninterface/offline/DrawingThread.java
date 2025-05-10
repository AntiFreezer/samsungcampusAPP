package com.example.aninterface.offline;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.SurfaceHolder;

import java.util.List;
import java.util.Objects;

public class DrawingThread extends Thread{
    //VARIABLES
    private boolean running;
    private final Paint paint;
    private int currentColor;
    private String currentShape;
    private int currentWidth;
    private boolean currentFog;
    private final SurfaceHolder surfaceHolder;
    private float x;
    private float y;
    private final Points points;
    public DrawingThread(SurfaceHolder surfaceHolder, Points points){
        running = true;
        this.points = points;
        points.clear();
        this.surfaceHolder = surfaceHolder;

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        currentColor = Color.RED;
        currentShape = "LINE";
        currentFog = false;
        currentWidth = 50;
        x = 0;
        y = 0;
    }


    //SETTERS
    public synchronized void setCurrentColor(int currentColor){
        this.currentColor = currentColor;
    }
    public synchronized void setCurrentShape(String currentShape) {
        this.currentShape = currentShape;
    }
    public synchronized void setCurrentWidth(int currentWidth){
        this.currentWidth = currentWidth;
    }
    public synchronized void setCurrentFog(boolean currentFog){
        this.currentFog = currentFog;
    }
    public synchronized void setStopRequest(){
        running = false;
    }
    private synchronized void setPoints(){
        if(!points.getPoints().isEmpty()){
        this.x = points.getPoints().get(points.getPoints().size() - 1)[0];
        this.y = points.getPoints().get(points.getPoints().size() - 1)[1];
        }
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
            Canvas canvas = surfaceHolder.lockCanvas();
             if(canvas != null){
                try{
                    //color
                    setCurrentColor(currentColor);
                    paint.setColor(currentColor);
                    //fog
                    int opacity = currentFog ? 127 : 255;
                    paint.setAlpha(opacity);
                    //width
                    setCurrentWidth(currentWidth);
                    paint.setStrokeWidth(currentWidth);
                    //shape
                    setPoints();
                    if(Objects.equals(getCurrentShape(), "LINE")){
                        List<Path> paths = points.getPaths();
                        synchronized (paths) {
                            for (Path path : paths) {
                                canvas.drawPath(path, paint);
                            }
                        }
                    }
                    else{
                        List<float[]> pointsList = points.getPoints();
                        synchronized (pointsList){
                            if(!pointsList.isEmpty()){
                                float [] lastPoint = pointsList.get(pointsList.size() - 1);
                                x = lastPoint[0];
                                y = lastPoint[1];
                                switch (getCurrentShape()){
                                    case "SQUARE":
                                        float halfWidth = getCurrentWidth() / 2f;
                                        canvas.drawRect(x - halfWidth, y - halfWidth, x + halfWidth, y + halfWidth, paint);
                                        break;
                                    case "CIRCLE":
                                        float radius = getCurrentWidth() / 2f;
                                        canvas.drawCircle(x, y, radius, paint);
                                        break;
                                }
                            }

                        }
                    }
                }finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }try {
                Thread.sleep(16);
            }catch (InterruptedException ignore){
            }
        }
    }
}