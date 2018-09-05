package com.yeah.ruisu.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class CustomCircle extends View
{
    public static final String TAG = "CustomCircle: ";

    int radius, borderColor, fillColor, minDiameter, width, height;

    Paint paint;

    public CustomCircle (Context context)
    {
        super(context);
        init(context, null, 0, 0);
    }

    public CustomCircle(Context context,
                        @Nullable AttributeSet attrs)
    {
        super(context, attrs);

        init(context, attrs, 0, 0);
    }

    public CustomCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        init(context, attrs, defStyleAttr, 0);
    }

    public CustomCircle(Context context,
                        @Nullable AttributeSet attrs,
                        int defStyleAttr,
                        int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init (Context context, AttributeSet attrs, int defStyle, int defStykeRes)
    {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomCircle);

        radius = typedArray.getInt(R.styleable.CustomCircle_radius, 0);
        fillColor = typedArray.getColor(R.styleable.CustomCircle_fillColor, 0);

        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        paint.setColor(fillColor);

        minDiameter = Math.min(getHeight(), getWidth());

        radius = Math.min(minDiameter/2, radius);

        canvas.drawCircle(width/2, height/2, radius, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,
                             int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int desiredWidth = 300;
        int desiredHeight = 300;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        /* Measure width. */
        if (widthMode == MeasureSpec.EXACTLY)
        {
            /* Must be this size. */
            width = widthSize;
        }
        else if (widthMode == MeasureSpec.AT_MOST)
        {
            /* Can't be bigger than... */
            width = Math.min(desiredHeight, widthSize);
        }
        else
        {
            /* Be whatever you want. */
            width = desiredWidth;
        }

        /* Measure Height. */
        if (heightMode == MeasureSpec.EXACTLY)
        {
            /* Must be this size. */
            height = heightSize;
        }
        else if (heightMode == MeasureSpec.AT_MOST)
        {
            /* Can't be bigger than.. */
            height = Math.min (desiredHeight, heightSize);
        }
        else
        {
            /* Be wahtever you want. */
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
    }

    public void setRadius(int radius)
    {
        this.radius = radius;
        invalidate();
    }

    public void setFillColor(int fillColor)
    {
        this.fillColor = fillColor;
        invalidate();
    }
}
