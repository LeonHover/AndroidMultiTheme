package io.github.leonhover.theme.base.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import java.lang.ref.SoftReference;

import io.github.leonhover.theme.R;

/**
 * Created by wangzongliang on 2016/11/8.
 */

public class CoverImageView extends AppCompatImageView {

    public static final String TAG = "CoverImageView";

    private Paint mPaint;
    private SoftReference<Bitmap> tmpBitmapReference = null;
    private SoftReference<Canvas> tmpCanvasReference = null;
    private int canvasWidth = -1;
    private int canvasHeight = -1;
    private int[] pixels;
    private int coverColor = -1;

    public CoverImageView(Context context) {
        super(context);
    }

    public CoverImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CoverImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CoverImageView);
        coverColor = ta.getColor(R.styleable.CoverImageView_coverColor, -1);
        ta.recycle();
    }

    public void setCoverColor(int color) {
        this.coverColor = color;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (coverColor < 0) {
            return;
        }

        int coverAlpha = coverColor & 0xff000000;

        if (coverAlpha == 0x00000000) {
            return;
        }

        if (canvas.getHeight() + canvas.getWidth() == 0) {
            return;
        }

        if (canvasHeight != canvas.getHeight() || canvasWidth != canvas.getWidth()) {
            canvasHeight = canvas.getHeight();
            canvasWidth = canvas.getWidth();
            pixels = new int[canvasWidth * canvasHeight];
        }

        Bitmap tmpBitmap = createCoverBitmap(canvasWidth, canvasHeight);

        Canvas tmpCanvas = createCoverCanvas(tmpBitmap);

        tmpCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        super.onDraw(tmpCanvas);


        tmpBitmap.getPixels(pixels, 0, canvasWidth, 0, 0, canvasWidth, canvasHeight);
        for (int i = 0; i < pixels.length; i++) {
            if (pixels[i] != 0x00000000) {
                pixels[i] = coverColor;
            }
        }
        tmpBitmap.setPixels(pixels, 0, canvasWidth, 0, 0, canvasWidth, canvasHeight);
        canvas.drawBitmap(tmpBitmap, 0, 0, mPaint);
    }

    private Bitmap createCoverBitmap(int width, int height) {
        Bitmap coverBitmap = null;

        if (tmpBitmapReference != null && tmpBitmapReference.get() != null) {
            coverBitmap = tmpBitmapReference.get();
            if (coverBitmap.getWidth() != width || coverBitmap.getHeight() != height || coverBitmap.isRecycled()) {
                coverBitmap = null;
            }
        }

        if (coverBitmap == null) {
            coverBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            tmpBitmapReference = new SoftReference<Bitmap>(coverBitmap);
        }

        return coverBitmap;
    }

    private Canvas createCoverCanvas(Bitmap bitmap) {
        Canvas coverCanvas = null;

        if (tmpCanvasReference != null && tmpCanvasReference.get() != null) {
            coverCanvas = tmpCanvasReference.get();
        }

        if (coverCanvas == null) {
            coverCanvas = new Canvas(bitmap);
            tmpCanvasReference = new SoftReference<Canvas>(coverCanvas);
        }

        return coverCanvas;
    }
}
