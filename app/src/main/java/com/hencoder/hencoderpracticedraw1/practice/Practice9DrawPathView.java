package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {

    Path pathHeart = new Path();
    Path pathCircle = new Path();

    {
        pathHeart.addArc(100, 100, 300, 300, -225, 225);
        pathHeart.arcTo(300, 100, 500, 300, -180, 225, false);
        pathHeart.lineTo(300, 450);

        pathCircle.addCircle(50, 50, 50, Path.Direction.CW);
        pathCircle.addCircle(90, 50, 50, Path.Direction.CW);
    }


    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);

        canvas.drawPath(pathHeart, paint);


        /**
         WINDING         (0),绕圈
         EVEN_ODD        (1),奇偶
         INVERSE_WINDING (2),
         INVERSE_EVEN_ODD(3);
         */
        pathCircle.setFillType(Path.FillType.INVERSE_WINDING);
        canvas.drawPath(pathCircle, paint);

    }
}
