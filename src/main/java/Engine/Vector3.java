package Engine;

/**
 * A 3 dimensional vector
 *
 * @author Dustin Ryan-Roepsch
 */
public class Vector3 {
    private static final int X = 0;
    private static final int Y = 1;
    private static final int Z = 2;
    protected float[] vector;

    public Vector3(float x, float y, float z) {
        vector = new float[4];
        vector[3] = 1;
        vector[X] = x;
        vector[Y] = y;
        vector[Z] = z;
    }

    public Vector3(float[] vector) {
        this(vector[X], vector[Y], vector[Z]);
    }
}
