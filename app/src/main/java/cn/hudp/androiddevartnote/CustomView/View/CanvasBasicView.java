package cn.hudp.androiddevartnote.CustomView.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.hudp.androiddevartnote.R;

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
        canvas.drawText("1.1.1.1 setColor", 30, 50, textPaint);

        paint.setColor(Color.parseColor("#009688"));
        canvas.drawRect(30, 70, 230, 190, paint);

        paint.setColor(Color.parseColor("#FF9800"));
        paint.setStrokeWidth(20);
        paint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawLine(300, 70, 450, 190, paint);

        paint.setColor(Color.parseColor("#E91E63"));
        paint.setTextSize(24);
        canvas.drawText("HenCoder", 500, 130, paint);

        // y 250
        canvas.drawText("1.1.1.2 setARGB", 30, 235, textPaint);

        paint.setARGB(100, 255, 0, 0);
        canvas.drawRect(30, 250, 230, 310, paint);

        paint.setARGB(100, 0, 0, 0);
        paint.setStrokeWidth(20);
        paint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawLine(310, 270, 450, 180, paint);

        // ~~~~~  y 300
        canvas.drawText("1.1.2  setShader", 30, 360, textPaint);  // 上面多40  下面留20

        canvas.drawText("1.1.2.1 LinearGradient 线性渐变", 60, 420, textPaint);

        Shader shader = new LinearGradient(60, 440, 220, 600, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP); //
        paint.setShader(shader);
        canvas.drawCircle(140, 520, 80, paint);

        Shader shader2 = new LinearGradient(60, 100, 100, 150, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.MIRROR); //
        paint.setShader(shader2);
        canvas.drawCircle(340, 520, 80, paint);

        Shader shader3 = new LinearGradient(60, 100, 100, 150, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.REPEAT); //
        paint.setShader(shader3);
        canvas.drawCircle(540, 520, 80, paint);

        // ~~~~~  y 600
        canvas.drawText("1.1.2.2 RadialGradient 辐射渐变", 60, 640, textPaint);

        Shader shader4 = new RadialGradient(140, 740, 80, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        paint.setShader(shader4);
        canvas.drawCircle(140, 740, 80, paint);

        // ~~~~~  y 820
        canvas.drawText("1.1.2.3 SweepGradient 扫描渐变", 60, 860, textPaint);

        Shader shader5 = new SweepGradient(140, 960, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"));
        paint.setShader(shader5);
        canvas.drawCircle(140, 960, 80, paint);

        // ~~~~~  y 1060
        canvas.drawText("1.1.2.4 BitmapShader", 60, 1080, textPaint);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.chaoren);
        Shader shader6 = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);
        paint.setShader(shader6);
        canvas.drawCircle(200, 1240, 140, paint);

        Shader shader7 = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        paint.setShader(shader7);
        canvas.drawRect(60, 1420, 460, 1660, paint);

        // ~~~~~  y 1700
        canvas.drawText("1.1.2.5 ComposeShader 混合着色器(相同Shader混合需关闭硬件加速)", 60, 1700, textPaint);

        Shader shader8 = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR);

        // 第二个 Shader：从上到下的线性渐变（由透明到黑色）
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.feng);
        Shader shader9 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        // ComposeShader：结合两个 Shader
        Shader composeShader = new ComposeShader(shader8, shader9, PorterDuff.Mode.SRC_OVER);
        paint.setShader(composeShader);
        canvas.drawCircle(160, 1820, 100, paint);
    }
}
