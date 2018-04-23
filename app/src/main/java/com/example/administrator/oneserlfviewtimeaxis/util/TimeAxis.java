package com.example.administrator.oneserlfviewtimeaxis.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.oneserlfviewtimeaxis.TempBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */

public class TimeAxis extends View {

    private List<TempBean> list = new ArrayList<>();

    private Paint linePaint;//画线的画笔
    private Paint stareCirclePaint;//画起始点的画笔
    private Paint endCirclePaint;//画结束点的画笔
    private Paint darkGrayTextPaint;  //写字的画笔 深色
    private Paint lightGrayTextPaint;  //写字的画笔

    private int width = 0;//控件的宽
    private int height = 0;//控件的高
    private int centre;//高的中心点
    private int mean;//平均宽

    public TimeAxis(Context context) {
        this(context, null);
    }

    public TimeAxis(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeAxis(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.WHITE);
        initView();
    }

    private void initView() {
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setDither(true);
        LinearGradient lg = new LinearGradient(30, centre, width - 40, centre, Color.parseColor("#C8F6F6F6"), Color.parseColor("#FFEAEAEA"), Shader.TileMode.MIRROR);
        linePaint.setShader(lg);
        linePaint.setStrokeWidth((float) 5);

        stareCirclePaint = new Paint();
        stareCirclePaint.setAntiAlias(true);
        stareCirclePaint.setDither(true);
        stareCirclePaint.setColor(Color.parseColor("#25BE4F"));

        endCirclePaint = new Paint();
        endCirclePaint.setAntiAlias(true);
        endCirclePaint.setDither(true);
        endCirclePaint.setColor(Color.parseColor("#F9A20B"));

        darkGrayTextPaint = new Paint();
        darkGrayTextPaint.setAntiAlias(true);
        darkGrayTextPaint.setDither(true);
        darkGrayTextPaint.setColor(Color.parseColor("#666666"));
        darkGrayTextPaint.setTextSize(20);


        lightGrayTextPaint = new Paint();
        lightGrayTextPaint.setAntiAlias(true);
        lightGrayTextPaint.setDither(true);
        lightGrayTextPaint.setColor(Color.parseColor("#999999"));
        lightGrayTextPaint.setTextSize(20);


//        #FFE252

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获得宽高的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //获得宽高的具体值
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(widthSize, heightSize);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        centre = height / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (list.size() <= 0) {
            return;
        }
        starDraw(canvas);
        middleDraw(canvas);
        endDraw(canvas);

    }

    private void endDraw(Canvas canvas) {
        //画结束的时间、价格、点
        canvas.drawText(list.get(list.size() - 1).getMoney() + "/天", width - 80, centre + 40, darkGrayTextPaint);//结束的价钱
        canvas.drawCircle(width - 40, centre, 10, endCirclePaint);//结束的圆点
        canvas.drawText(list.get(list.size() - 1).getDate(), width - 80, centre - 60, darkGrayTextPaint);//结束的时间
        canvas.drawText("预售结束", width - 80, centre - 30, lightGrayTextPaint);
    }

    private void middleDraw(Canvas canvas) {
        //画中间的点和价钱
        for (int i = 1; i < 4; i++) {
            canvas.drawCircle(30 + mean * i, centre, 6, lightGrayTextPaint);
            canvas.drawText(list.get(i).getMoney() + "/天", 10 + mean * i, centre + 40, lightGrayTextPaint);
        }
    }

    private void starDraw(Canvas canvas) {
        //画线
        canvas.drawLine(30, centre, width - 30, centre, linePaint);
        //画开始的数据
        canvas.drawText(list.get(0).getMoney() + "/天", 10, centre + 40, darkGrayTextPaint);//开始的价钱
        canvas.drawCircle(30, centre, 10, stareCirclePaint);//开始的点
        canvas.drawText(list.get(0).getDate(), 10, centre - 60, darkGrayTextPaint);//开始的时间
        canvas.drawText("当前价格", 10, centre - 30, lightGrayTextPaint);
    }

    public void updateList(List<TempBean> list) {
        this.list.addAll(list);
        if (list.size() > 5) {
            mean = ((width - 70) * 3 / 4) / 4;
        } else {
            mean = (width - 70) / list.size();
        }
        invalidate();
    }

}