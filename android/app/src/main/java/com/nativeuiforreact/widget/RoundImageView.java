package com.nativeuiforreact.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by king on 2018/9/6.
 */

public class RoundImageView extends ImageView {

    private Paint mPaint;
    private int mWidth;
    private int mRadius;
    private RectF mRect;
    private int mRoundRadius;
    private BitmapShader mBitmapShader;
    private Matrix mMatrix;
    private int mType = 1;

    public static final int TYPE_CIRCLE = 1;
    public static final int TYPE_ROUND_RECTANGLE = 2;
    public static final int TYPE_OVAL = 3;
    public static final int DEFAUT_RADIUS = 5;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mMatrix = new Matrix();
        mRoundRadius = DEFAUT_RADIUS;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mType == TYPE_CIRCLE) {
            mWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());
            mRadius = mWidth / 2;
            setMeasuredDimension(mWidth, mWidth);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (null == getDrawable()) {
            return;
        }
        initBitmapShaderByType();
        switch (mType) {
            case TYPE_CIRCLE:
                canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
                break;
            case TYPE_ROUND_RECTANGLE:
                mPaint.setColor(Color.RED);
                canvas.drawRoundRect(mRect, mRoundRadius, mRoundRadius, mPaint);
                break;
            case TYPE_OVAL:
                canvas.drawOval(mRect, mPaint);
                break;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRect = new RectF(0, 0, getWidth(), getHeight());
    }

    private void initBitmapShaderByType() {
        Drawable drawable = getDrawable();
        if (null == drawable) {
            return;
        }
        Bitmap bitmap = drawableToBitmap(drawable);
        mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;
        switch (mType) {
            case TYPE_CIRCLE:
                int bSize = Math.min(bitmap.getWidth(), bitmap.getHeight());
                scale = mWidth * 1.0f / bSize;
                break;
            case TYPE_ROUND_RECTANGLE:
            case TYPE_OVAL:
                scale = Math.max(getWidth() * 1.0f / bitmap.getWidth(), getHeight() * 1.0f / bitmap.getHeight());
                break;
        }
        mMatrix.setScale(scale, scale);
        mBitmapShader.setLocalMatrix(mMatrix);
        mPaint.setShader(mBitmapShader);
    }

    public void setImageName(String name) {
        this.setImageResource(getResourceId(name));
    }

    public void setType(String typeStr) {
        int type = 1;
        switch (typeStr) {
            case "circle":
                type = 1;
                break;
            case "round":
                type = 2;
                break;
            case "oval":
                type = 3;
                break;
        }
        if (this.mType != type) {
            this.mType = type;
            invalidate();
        }
    }

    public void setRoundRadius(int mRoundRadius) {
        if (this.mRoundRadius != mRoundRadius) {
            this.mRoundRadius = mRoundRadius;
            invalidate();
        }
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            return bitmapDrawable.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    private int getResourceId(String imageName) {
        int resId = getResources().getIdentifier(imageName, "mipmap", getContext().getPackageName());
        if (resId == 0) {
            resId = getResources().getIdentifier(imageName, "drawable", getContext().getPackageName());
        }
        return resId;
    }
}
