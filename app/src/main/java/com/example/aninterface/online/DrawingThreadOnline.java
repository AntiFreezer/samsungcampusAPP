package com.example.aninterface.online;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.view.SurfaceHolder;

import com.example.aninterface.offline.PointsOffline;

import java.util.List;
import java.util.Objects;

public class DrawingThreadOnline extends Thread{
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
    private final PointsOnline points;
    public DrawingThreadOnline(SurfaceHolder surfaceHolder, PointsOnline points){
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

    //THREAD
    @Override
    public void run(){
        while (running){
            Canvas canvas = surfaceHolder.lockCanvas();
            if(canvas != null){
                try {
                    synchronized (points){
                        List<float[]> shapeLists = points.getPoints();
                        List<Integer> shapeColors = points.getShapeColors();
                        List<Integer> shapeWidths = points.getShapeWidths();
                        List<Boolean> shapeFogs = points.getShapeFogs();
                        List<String> shapeTypes = points.getShapeTypes();

                        for (int i = 0; i < shapeLists.size(); i++) {
                            float[] point = shapeLists.get(i);
                            paint.setColor(shapeColors.get(i));
                            paint.setXfermode(null);
                            paint.setStrokeWidth(shapeWidths.get(i));
                            if (shapeFogs.get(i)) {
                                paint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
                            } else {
                                paint.setMaskFilter(null);
                            }
                            x = point[0];
                            y = point[1];
                            String shapeType = shapeTypes.get(i);
                            switch (shapeType) {
                                case "SQUARE":
                                    float halfWidth = shapeWidths.get(i) / 2f;
                                    canvas.drawRect(x - halfWidth, y - halfWidth,
                                            x + halfWidth, y + halfWidth, paint);
                                    break;
                                case "CIRCLE":
                                    float radius = shapeWidths.get(i) / 2f;
                                    canvas.drawCircle(x, y, radius, paint);
                                    break;
                            }
                        }
                        List<Path> paths = points.getPaths();
                        List<Integer> pathColors = points.getPathColors();
                        List<Integer> pathWidths = points.getPathWidths();
                        List<Boolean> pathFogs = points.getPathFogs();
                        List<String> pathTypes = points.getPathTypes();

                        if (!paths.isEmpty()) {
                            for (int i = 0; i < paths.size(); i++) {
                                paint.setColor(pathColors.get(i));
                                paint.setStrokeWidth(pathWidths.get(i));
                                if (pathFogs.get(i)) {
                                    paint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));
                                } else {
                                    paint.setMaskFilter(null);
                                }
                                String pathType = pathTypes.get(i);
                                switch (pathType){
                                    case("LINE"):
                                        canvas.drawPath(paths.get(i), paint);
                                    break;
                                    case("ERASER"):
                                        paint.setColor(Color.BLACK);
                                        paint.setMaskFilter(null);
                                        canvas.drawPath(paths.get(i), paint);
                                    break;
                                }
                            }
                        }

                    }
                }
                finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

}