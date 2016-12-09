package com.dustinroepsch.SoftwareRenderer.Utilities;

import java.awt.image.BufferedImage;

/**
 * A collection of useful utilities for manipulation of BufferedImage's
 */
public class BufferedImageUtilities {
    /**
     * An implementation of Bresenham's Line algorithm, using the
     * "Third Attempt" found at https://github.com/ssloy/tinyrenderer/wiki/Lesson-1:-Bresenham%E2%80%99s-Line-Drawing-Algorithm
     *
     * @param img The Buffered Image to write the line to.
     * @param x0  The starting x value.
     * @param y0  The starting y value.
     * @param x1  The ending x value.
     * @param y1  The ending y value.
     * @param rgb The color of the line.
     */
    public static void line(BufferedImage img, int x0, int y0, int x1, int y1, int rgb) {
        boolean steep = false;
        int temp;
        if (Math.abs(x0 - x1) < Math.abs(y0 - y1)) {
            //line is steep, so transpose
            //swap x0 and y0
            temp = x0;
            x0 = y0;
            y0 = temp;
            //swap x1 and y1
            temp = x1;
            x1 = y1;
            y1 = temp;
            steep = true;
        }
        if (x0 > x1) {
            //make it left to right
            temp = x0;
            x0 = x1;
            x1 = temp;
            temp = y0;
            y0 = y1;
            y1 = temp;
        }
        for (int x = x0; x <= x1; x++) {
            float t = (x - x0) / ((float) (x1 - x0));
            int y = (int) (y0 * (1 - t) + y1 * t);
            if (steep) {
                img.setRGB(y, x, rgb); //detranspose
            } else {
                img.setRGB(x, y, rgb);
            }
        }
    }
}
