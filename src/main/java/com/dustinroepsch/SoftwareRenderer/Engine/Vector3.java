package com.dustinroepsch.SoftwareRenderer.Engine;

import static com.google.common.base.Preconditions.*;

/**
 * A 3 dimensional vector
 * These vectors are represented as 4 element arrays
 * with the last element set to 1, so that TransformationMatrix class can
 * translate them.
 *
 * @author Dustin Ryan-Roepsch
 */
public class Vector3 {
    private static final int X = 0;
    private static final int Y = 1;
    private static final int Z = 2;
    protected float[] vector;

    /**
     * Constructs a 3-vector with the given x y and z coordinates
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param z The z coordinate.
     */
    public Vector3(float x, float y, float z) {
        vector = new float[4];
        vector[3] = 1;
        vector[X] = x;
        vector[Y] = y;
        vector[Z] = z;
    }

    /**
     * Constructs a 3-vector using the given array.
     *
     * @param vector Must have length >= 3. The 0th element is X, 1st element is Y and 2nd element is Z
     */
    public Vector3(float[] vector) {
        checkNotNull(vector);
        checkArgument(vector.length >= 3, "Vector.length is less than 3");
        this.vector = new float[4];
        for (int i = 0; i < 3; i++) {
            this.vector[i] = vector[i];
        }
        vector[3] = 1;
    }
}
