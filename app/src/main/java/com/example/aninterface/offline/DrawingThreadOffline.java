package com.example.aninterface.offline;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.SurfaceHolder;

import java.util.List;

public class DrawingThreadOffline extends Thread {
    private boolean running;
    private final Paint paint;
    private int currentColor;
    private String currentShape;
    private int currentWidth;
    private boolean currentFog;
    private final SurfaceHolder surfaceHolder;
    private final PointsOffline points;

    public DrawingThreadOffline(SurfaceHolder surfaceHolder, PointsOffline points){
        running = true;
        this.points = points;
        points.clear();
        this.surfaceHolder = surfaceHolder;

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        currentColor = Color.RED;
        currentShape = "LINE";
        currentFog = false;
        currentWidth = 50;
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

    //GETTERS
    public String getCurrentShape(){
        return this.currentShape;
    }
    public int getCurrentWidth(){
        return currentWidth;
    }
    public int getCurrentColor(){
        return currentColor;
    }
    public boolean getCurrentFog() {
        return currentFog;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    synchronized (points) {
                        // Get all the items
                        List<DrawingItemOffline> allItems = points.getAllItems();
                        paint.setPathEffect(new CornerPathEffect(1));

                        for (DrawingItemOffline item : allItems) {
                            if (item.isPath()) {
                                // Paths(Lines and Erasers)
                                paint.setColor(item.getColor());
                                paint.setStrokeWidth(item.getWidth());
                                paint.setMaskFilter(item.hasFog() ?
                                        new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL) : null);

                                canvas.drawPath(item.getPath(), paint);
                            } else {
                                // Shapes(Circles and Squares)
                                paint.setColor(item.getColor());
                                paint.setStrokeWidth(item.getWidth());
                                paint.setMaskFilter(item.hasFog() ?
                                        new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL) : null);

                                float x = item.getX();
                                float y = item.getY();
                                switch (item.getType()) {
                                    case "SQUARE":
                                        float half = item.getWidth() / 2f;
                                        canvas.drawRect(x - half, y - half,
                                                x + half, y + half, paint);
                                        break;
                                    case "CIRCLE":
                                        canvas.drawCircle(x, y, item.getWidth() / 2f, paint);
                                        break;
                                }
                            }
                        }
                    }
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}