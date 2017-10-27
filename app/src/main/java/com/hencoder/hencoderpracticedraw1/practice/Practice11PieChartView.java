package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.model.Data;

import java.util.ArrayList;

public class Practice11PieChartView extends View {

    private static final String NAME = "直方图";

    private Paint paint;
    private ArrayList<Data> dataList;
    private float maxNum;
    private float totalNum;
    private float width;
    private float space;
    private float startX;
    private RectF rectF;
    private float radius;

    /**
     * 开始的角度
     */
    private float startAngle;

    /**
     * 扫过的角度
     */
    private float sweepAngle;

    /**
     * 当前扇形一半的角度
     */
    private float curArcHalfAngle;

    /**
     * 直线开始的X坐标
     */
    float lineStartX = 0f;

    /**
     * 直线开始的Y坐标
     */
    float lineStartY = 0f;

    /**
     * 直线结束的X坐标
     */
    float lineEndX;

    /**
     * 直线结束的Y坐标
     */
    float lineEndY;

    public Practice11PieChartView(Context context) {
        super(context);
        init();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        dataList = new ArrayList<>();
        dataList.add(new Data("Froyo", 10.0f, Color.GREEN));
        dataList.add(new Data("ICS", 18.0f, Color.BLUE));
        dataList.add(new Data("JB", 22.0f, Color.RED));
        dataList.add(new Data("KK", 80.0f, Color.GRAY));
        dataList.add(new Data("L", 40.0f, Color.YELLOW));
        dataList.add(new Data("M", 60.0f, Color.CYAN));

        totalNum = 0f;
        maxNum = 0f;
        for (Data data : dataList) {
            if (data != null) {
                totalNum += data.getNumber();
                maxNum = Math.max(maxNum, data.getNumber());
            }
        }

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(2);
        paint.setTextSize(30);

        radius = 200f;
        rectF = new RectF(-200, -200, 200, 200);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图

        //将画布（0，0）移动到画布中心
        canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);

        for (Data data : dataList) {
            if (data == null) {
                continue;
            }
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(data.getColor());

            sweepAngle = data.getNumber() / totalNum * 360f;
            curArcHalfAngle = startAngle + sweepAngle * 0.5f;

            lineStartX = radius * (float) Math.cos(curArcHalfAngle * Math.PI / 180);
            lineStartY = radius * (float) Math.sin(curArcHalfAngle * Math.PI / 180);
            lineEndX = (radius + 30) * (float) Math.cos(curArcHalfAngle * Math.PI / 180);
            lineEndY = (radius + 30) * (float) Math.sin(curArcHalfAngle * Math.PI / 180);

            if (maxNum == data.getNumber()) {
                canvas.save();
                canvas.translate(lineEndX * 0.1f, lineEndY * 0.1f);
                canvas.drawArc(rectF, startAngle, sweepAngle, true, paint);
            } else {
                canvas.drawArc(rectF, startAngle, sweepAngle - 1.0f, true, paint);
            }


            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawLine(lineStartX, lineStartY, lineEndX, lineEndY, paint);


            if (curArcHalfAngle > 90 && curArcHalfAngle <= 270) {
                canvas.drawLine(lineEndX, lineEndY, lineEndX - 50, lineEndY, paint);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawText(data.getName(), lineEndX - 50 - 10 - paint.measureText(data.getName()), lineEndY, paint);
            } else {
                canvas.drawLine(lineEndX, lineEndY, lineEndX + 50, lineEndY, paint);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawText(data.getName(), lineEndX + 50 + 10, lineEndY, paint);
            }

            if (maxNum == data.getNumber()) {
                canvas.restore();
            }
            startAngle += sweepAngle;
        }


//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//
//        RectF oneCircle = new RectF(90, 10, 390, 310);
//        RectF twoCircle = new RectF(100, 20, 400, 320);
//
//        paint.setColor(Color.parseColor("#82065e"));
//        canvas.drawArc(twoCircle,0,10,true,paint);
//
//        paint.setColor(Color.parseColor("#4f494d"));
//        canvas.drawArc(twoCircle,15,10,true,paint);
//
//        paint.setColor(Color.parseColor("#2c9228"));
//        canvas.drawArc(twoCircle,30,50,true,paint);
//
//        paint.setColor(Color.parseColor("#3292ef"));
//        canvas.drawArc(twoCircle,85,95,true,paint);
//
//        paint.setColor(Color.parseColor("#ff0000"));
//        canvas.drawArc(oneCircle,190,110,true,paint);
//
//        paint.setColor(Color.parseColor("#f7ba3d"));
//        canvas.drawArc(twoCircle,-50,40,true,paint);
    }
}
