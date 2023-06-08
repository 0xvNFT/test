package com.tk88congcu03phat.tk88.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public final class Utils {

    private Utils() {}


    public static String readText(InputStream is) throws IOException {
        return readText(is, "UTF8");
    }


    public static String readText(InputStream is, String charSetName) throws IOException {

        InputStreamReader isr = new InputStreamReader(is, charSetName);
        BufferedReader reader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        reader.close();

        return sb.toString();
    }


    public static void writeText(OutputStream os, String content) throws IOException {
        writeText(os, content, "UTF8");
    }


    public static void writeText(OutputStream os, String content, String charSetName) throws IOException {

        // final OutputStream out = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        OutputStreamWriter writer = new OutputStreamWriter(os, charSetName);
        writer.write(content);
        writer.flush();
        os.close();
    }


    public static void removeOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener listener) {
        view.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
    }


    public static Drawable getDrawable(Resources resources, int id) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT < 22) {
            //noinspection deprecation
            drawable = resources.getDrawable(id);
        } else {
            drawable = resources.getDrawable(id, null);
        }
        return drawable;
    }

    public static void setBackground(View view, Drawable drawable) {
        view.setBackground(drawable);
    }


    public static Bitmap replaceColorInBitmap(Bitmap sourceBitmap, int sourceColor, int targetColor) {

        int width =  sourceBitmap.getWidth();
        int height = sourceBitmap.getHeight();
        int number_pixels = width * height;
        int[] pixels = new int[number_pixels];
        sourceBitmap.getPixels(pixels, 0, width, 0, 0, width, height);

        for (int i = 0; i < number_pixels; i++) {
            if (pixels[i] == sourceColor) {
                pixels[i] = targetColor;
            }
        }

        // set the pixels in the output bitmap
        Bitmap targetBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        targetBitmap.setPixels(pixels, 0, width, 0, 0, width, height);

        return targetBitmap;
    }



    public static <T> T verifyNotNull(T reference) throws NullPointerException {
        if (null == reference) {
            throw new NullPointerException();
        }
        return reference;
    }


    public static int calculateInSampleSize(int availableWidth, int availableHeight, int requiredWidth, int requiredHeight) {
        int inSampleSize = 1;

        if (availableHeight > requiredHeight || availableWidth > requiredWidth) {

            final int halfHeight = availableHeight / 2;
            final int halfWidth = availableWidth / 2;

            while ((halfHeight / inSampleSize) >= requiredHeight && (halfWidth / inSampleSize) >= requiredWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


    public static Bitmap decodeSampledBitmapFromStream(InputStreamProvider inputStreamProvider, int requiredWidth, int requiredHeight) {

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStreamProvider.getStream(), null, options);

        options.inSampleSize = calculateInSampleSize(options.outWidth, options.outHeight, requiredWidth, requiredHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(inputStreamProvider.getStream(), null, options);
    }
}
