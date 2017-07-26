package cn.hudp.androiddevartnote.CustomView.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by HuDP on 2017/7/26.
 */

public class CanvasBasicView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CanvasBasicView(Context context) {
        super(context);
    }

    public CanvasBasicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasBasicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        textPaint.setColor(Color.parseColor("#000000"));
        textPaint.setTextSize(30);
        canvas.drawText("1.1.1 setColor", 30, 45, textPaint);

//        paint.setDither(true);
        paint.setColor(Color.parseColor("#009688"));
        canvas.drawRect(30, 60, 230, 180, paint);

        paint.setColor(Color.parseColor("#FF9800"));
        paint.setStrokeWidth(20);
        paint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawLine(300, 60, 450, 180, paint);

        paint.setColor(Color.parseColor("#E91E63"));
        canvas.drawText("HenCoder", 500, 130, paint);

        // y 250 start
        canvas.drawText("1.1.2 setARGB", 30, 235, textPaint);

        paint.setARGB(100, 255, 0, 0);
        canvas.drawRect(30, 250, 230, 300, paint);

        paint.setARGB(100, 0, 0, 0);
        paint.setStrokeWidth(20);
        paint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawLine(300, 270, 450, 180, paint);
    }
}
