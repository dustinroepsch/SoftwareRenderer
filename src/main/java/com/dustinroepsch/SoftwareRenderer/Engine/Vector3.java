package com.dustinroepsch.SoftwareRenderer.Engine;

import java.util.Arrays;

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
        this.vector[3] = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector3 vector3 = (Vector3) o;

        return Arrays.equals(vector, vector3.vector);

    }

    /**
     * Compares this Vector3 to another using tolerance epsilon
     *
     * @param other   The Vector3 to compare this Vector3 to.
     * @param epsilon The tolerance of the comparison
     * @return Whether the Vectors components are within epsilon of each other.
     */
    public boolean epsilonEquals(Vector3 other, float epsilon) {
        for (int i = 0; i < 4; i++) {
            if (Math.abs(this.vector[i] - other.vector[i]) > epsilon) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vector);
    }

    @Override
    public String toString() {
        return "Vector3{" +
                "vector=" + Arrays.toString(vector) +
                '}';
    }
}
