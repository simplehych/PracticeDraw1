package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.model.Data;

import java.util.ArrayList;

public class Practice10HistogramView extends View {

    private static final String NAME = "直方图";

    private Paint paint;
    private ArrayList<Data> dataList;
    private float maxNum;
    private float width;
    private float space;
    private float startX;

    public Practice10HistogramView(Context context) {
        super(context);
        init();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        dataList = new ArrayList<>();
        dataList.add(new Data("Froyo", 10.0f, Color.GREEN));
        dataList.add(new Data("ICS", 18.0f, Color.GREEN));
        dataList.add(new Data("JB", 22.0f, Color.GREEN));
        dataList.add(new Data("KK", 27.0f, Color.GREEN));
        dataList.add(new Data("L", 40.0f, Color.GREEN));
        dataList.add(new Data("M", 60.0f, Color.GREEN));

        maxNum = 0f;
        for (Data data : dataList) {
            if (data != null) {
                maxNum = Math.max(maxNum, data.getNumber());
            }
        }

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(2);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图

        /**
         * 画直方图名称
         */
        paint.setColor(Color.WHITE);
        paint.setTextSize(36);
        canvas.drawText(NAME, (canvas.getWidth() - paint.measureText(NAME)) * 0.5f, canvas.getHeight() * 0.9f, paint);

        //将画布原点移动到直方图原点位置
        canvas.translate(canvas.getWidth() * 0.1f, canvas.getHeight() * 0.7f);

        width = (canvas.getWidth() * 0.8f - 100) / dataList.size() * 0.8f;
        space = (canvas.getWidth() * 0.8f - 100) / dataList.size() * 0.2f;

        /**
         * 画坐标轴
         */
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(0, 0, canvas.getWidth() * 0.8f, 0, paint);
        canvas.drawLine(0, 0, 0, -canvas.getHeight() * 0.6f, paint);

        startX = 0f;

        paint.setTextSize(18);
        paint.setStyle(Paint.Style.FILL);
        for (Data data : dataList) {
            if (data != null) {
                paint.setColor(data.getColor());
                canvas.drawRect(startX + space,
                        -(data.getNumber() / maxNum * canvas.getHeight() * 0.6f),
                        startX + space + width,
                        0, paint);

                paint.setColor(Color.WHITE);
                canvas.drawText(data.getName(), startX + space + (width - paint.measureText(data.getName())) / 2, 20, paint);

                startX += space + width;
            }
        }


//        /**
//         * 画背景
//         */
//        canvas.drawColor(Color.GRAY);
//
//        /**
//         * 画坐标
//         */
//        paint.setColor(Color.WHITE);
//        paint.setStyle(Paint.Style.STROKE);
//        Path path = new Path();
//        path.moveTo(50, 50);
//        path.lineTo(50, 400);
//        path.lineTo(1400, 400);
//        canvas.drawPath(path, paint);
//
//        canvas.drawLine(52, 50, 52, 398, paint);
//        canvas.drawLine(52, 398, 1400, 398, paint);
//
//
//        /**
//         * 画文字
//         */
//        canvas.drawText("O", 45, 410, paint);
//        canvas.drawText("Froyo", 60, 410, paint);
//        canvas.drawText("GB", 110, 410, paint);
//        canvas.drawText("ICS", 160, 410, paint);
//        canvas.drawText("JB", 210, 410, paint);
//        canvas.drawText("KitKat", 260, 410, paint);
//        canvas.drawText("L", 310, 410, paint);
//        canvas.drawText("M", 360, 410, paint);
//
//
//        /**
//         * 画矩形
//         */
//
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.GREEN);
//
//        canvas.drawRect(new RectF(60, 390, 100, 400), paint);
//        canvas.drawRect(new RectF(110, 370, 150, 400), paint);
//        canvas.drawRect(new RectF(160, 370, 200, 400), paint);
//        canvas.drawRect(new RectF(210, 200, 250, 400), paint);
//        canvas.drawRect(new RectF(260, 100, 300, 400), paint);
//        canvas.drawRect(new RectF(310, 20, 350, 400), paint);
//        canvas.drawRect(new RectF(360, 300, 400, 400), paint);

    }
}
