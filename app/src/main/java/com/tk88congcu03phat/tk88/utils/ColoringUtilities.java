package com.tk88congcu03phat.tk88.utils;

import android.content.Context;
import android.graphics.Color;

public final class ColoringUtilities {

    private final static float DARKEN_LIGHTEN_FACTOR = 0.8f;

    private ColoringUtilities() {}

    public static void deleteErrorLogFile(Context context) {

    }

    public static int[] colorSelectionButtonBackgroundGradient(int color) {
        int[] gradientColors = new int[2];
        float[] hsv = new float[3];

        Color.colorToHSV(color, hsv);
        hsv[2] *= DARKEN_LIGHTEN_FACTOR;
        gradientColors[0] = Color.HSVToColor(hsv);

        Color.colorToHSV(color, hsv);
        hsv[2] = 1 - DARKEN_LIGHTEN_FACTOR * (1 - hsv[2]);
        gradientColors[1] = Color.HSVToColor(hsv);

        return gradientColors;
    }
}
