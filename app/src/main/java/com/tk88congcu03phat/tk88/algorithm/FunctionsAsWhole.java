package com.tk88congcu03phat.tk88.algorithm;

import com.tk88congcu03phat.tk88.utils.Vector2D;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;


public class FunctionsAsWhole {

    private FunctionsAsWhole() {}


    public static void simple_fill(Vector2D position, byte[] mask, int[] data, int width, int height, int value) {

        Queue<Vector2D> queue = new ArrayDeque<>();
        queue.add(position);

        final Vector2D ex = new Vector2D(1, 0);
        final Vector2D ey = new Vector2D(0, 1);

        while (!queue.isEmpty()) {

            Vector2D p = queue.remove();
            int i = p.x + p.y * width;

            if (mask[i] != 0) {
                mask[i] = 0;
                data[i] = value;

                if (p.y > 0) {
                    queue.add(Vector2D.subtract(p, ey));
                }
                if (p.y < height - 1) {
                    queue.add(Vector2D.add(p, ey));
                }
                if (p.x > 0) {
                    queue.add(Vector2D.subtract(p, ex));
                }
                if (p.x < width - 1) {
                    queue.add(Vector2D.add(p, ex));
                }
            }
        }
    }


    public static void advanced_fill(Vector2D position, byte[] mask, int[] data, int width, int height, int value) {

        Queue<LineSegment> queue = new ArrayDeque<>();
        queue.add(new LineSegment(position));


        while (!queue.isEmpty()) {
            LineSegment l = queue.remove();

            int o = l.y * width;

            int xl = l.xl;
            while (xl <= l.xr && mask[o + xl] == 0) {
                xl++;
            }
            if (xl <= l.xr) {

                int xr = xl;

                while (xl > 0 && mask[o + xl - 1] != 0) {
                    xl--;
                }
                while (xl <= l.xr) {

                    while (xr < width - 1 && mask[o + xr + 1] != 0) {
                        xr++;
                    }

                    Arrays.fill(mask, o + xl, o + xr + 1, (byte) 0);
                    Arrays.fill(data, o + xl, o + xr + 1, value);


                    if (l.y > 0) {
                        queue.add(new LineSegment(xl, xr, l.y - 1));
                    }
                    if (l.y < height - 1) {
                        queue.add(new LineSegment(xl, xr, l.y + 1));
                    }

                    xl = xr + 1;
                    while (xl <= l.xr && mask[o + xl] == 0) {
                        xl++;
                    }

                    xr = xl;
                }
            }
        }
    }


    private static class LineSegment {
        final int xl;
        final int xr;
        final int y;

        LineSegment(int xl, int xr, int y) {
            this.xl = xl;
            this.xr = xr;
            this.y = y;
        }

        LineSegment(Vector2D p) {
            xl = p.x;
            xr = p.x;
            y = p.y;
        }
    }
}
