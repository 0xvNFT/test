package com.tk88congcu03phat.tk88.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.tk88congcu03phat.tk88.algorithm.FunctionsAsWhole;
import com.tk88congcu03phat.tk88.ui.home.ImageHomeActivity;
import com.tk88congcu03phat.tk88.utils.Vector2D;

import java.util.Arrays;



public class ViewerCustomization extends View {


    private Bitmap bitmap;
    private Vector2D offset;
    private byte[] fill_mask;
    private int[] bitmap_pixels;

    private boolean modified = false;

    public ViewerCustomization(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);

        if (bitmap != null) {
            canvas.drawBitmap(bitmap, offset.x, offset.y, null);
        }
    }


    public void setBitmap(Bitmap bitmap) {
        // scale so that aspect ratio is kept and canvas is filled

        // TODO different modes: stretch, minimal scale, something in between

        // compute desired scale
        float width_scale_factor = (float) bitmap.getWidth() / getWidth();
        float height_scale_factor = (float) bitmap.getHeight() / getHeight();

        // maximal scale factor (everything is inside)
        float scale_factor = Math.max(width_scale_factor, height_scale_factor);

        // TODO limit not scaling more than

        // scale with a single scale factor (keeps aspect ratio)
        int width = (int) Math.floor(bitmap.getWidth() / scale_factor);
        int height = (int) Math.floor(bitmap.getHeight() / scale_factor);

        // scale bitmap
        this.bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);

        // compute offset
        offset = new Vector2D((int) Math.floor((getWidth() - width) / 2), (int) Math.floor((getHeight() - height) / 2));

        // get pixel information from bitmap in int array (a copy is made)
        int n = width * height;
        bitmap_pixels = new int[n];
        this.bitmap.getPixels(bitmap_pixels, 0, width, 0, 0, width, height);

        // create fill_mask from bitmap_pixels (== 0 for non-white, != 0 (=1) for white (which can be filled))
        fill_mask = new byte[n];
        for (int i = 0; i < n; i++) {
            // just test for the second byte (is faster)
            if (((bitmap_pixels[i] >> 16) & 0xff) == 255) {
                fill_mask[i] = 1;
            }
        }
    }


    public Bitmap getBitmap() {
        return this.bitmap;
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            Vector2D p = new Vector2D((int) event.getX() - offset.x,(int) event.getY() - offset.y);

            if (p.x >= 0 && p.x < bitmap.getWidth() && p.y >= 0 && p.y < bitmap.getHeight()) {

                color(p);
            }
            performClick();
        }
        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }


    private void color(Vector2D p) {

        int color = ImageHomeActivity.colorCurrent;

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        if (fill_mask[p.x + p.y * width] != 0 && bitmap_pixels[p.x + p.y * width] != color) {
            byte[] temporary_mask = Arrays.copyOf(fill_mask, fill_mask.length);

            long t0 = System.nanoTime();
            FunctionsAsWhole.advanced_fill(p, temporary_mask, bitmap_pixels, width, height, color);

            long t1 = System.nanoTime();


            long t2 = System.nanoTime();
            bitmap.setPixels(bitmap_pixels, 0, width, 0, 0, width, height);
            long t3 = System.nanoTime();

            Log.v("COL", String.format("fill algorithm: %.4fms", (t1 - t0) / 1e6));
            Log.v("COL", String.format("copy pixels:    %.4fms", (t3 - t2) / 1e6));
            Log.v("COL", String.format("width %d, height %d", width, height));

            this.modified = true;

            invalidate();
        }
    }


    public boolean isModified() {
        return this.modified;
    }
}