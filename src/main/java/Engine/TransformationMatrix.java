package Engine;

import java.util.Arrays;
import java.util.Random;
import java.util.jar.Pack200;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Transformation Matrix as described in
 * http://www.codinglabs.net/article_world_view_projection_matrix.aspx
 *
 * @author Dustin Ryan-Roepsch
 */
public class TransformationMatrix {
    private float[][] matrix;

    /**
     * Creates the identity transformation matrix (no rotation, no translation);
     */
    public TransformationMatrix() {
        matrix = new float[4][4];
        //setup the identity matrix
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                matrix[row][col] = row == col ? 1 : 0;
            }
        }
    }

    /**
     * Constructs a transformation matrix from a predefined matrix
     *
     * @param matrix The predefiend matrix.
     */
    private TransformationMatrix(float[][] matrix) {
        this.matrix = new float[4][4];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                this.matrix[row][col] = matrix[row][col];
            }
        }
    }


    /**
     * Multiplies this transformation matrix by a vector and returns the result.
     *
     * @param vector3 the vector to transform.
     * @return the result of the transformation.
     */
    public Vector3 applyTo(Vector3 vector3) {
        float[] vector = vector3.vector;
        float[] result = new float[4];
        for (int i = 0; i < 4; i++) {
            result[i] = matrix[i][0] * vector[0] + matrix[i][1] * vector[1] + matrix[i][2] * vector[2] + matrix[i][3] * vector[3];
        }
        return new Vector3(result);
    }

    /**
     * Applies this transformation matrix to another transformation matrix and returns the result.
     * Uses the iterative method found at https://en.wikipedia.org/wiki/Matrix_multiplication_algorithm
     *
     * @param other The matrix to apply this matrix to.
     * @return The transformed matrix.
     */
    public TransformationMatrix applyTo(TransformationMatrix other) {
        float[][] result = new float[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                float sum = 0;
                for (int k = 0; k < 4; k++) {
                    sum = sum + this.matrix[i][k] * other.matrix[k][j];
                }
                result[i][j] = sum;
            }
        }
        return new TransformationMatrix(result);
    }

    /**
     * Fills the matrix with random values. Preserves the bottom right 1, and the bottom row zeros.
     */
    public void randomize() {
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = random.nextFloat();
            }
        }
        matrix[3][0] = 0;
        matrix[3][1] = 0;
        matrix[3][2] = 0;
        matrix[3][3] = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransformationMatrix that = (TransformationMatrix) o;

        return Arrays.deepEquals(matrix, that.matrix);

    }

    /**
     * Returns a transformation that will rotate around the x axis.
     *
     * @param theta the angle to rotate in radians.
     * @return The transformation representation of the rotation.
     */
    public static TransformationMatrix getXrotation(float theta) {
        TransformationMatrix rotation = new TransformationMatrix();
        rotation.matrix[1][1] = (float) cos(theta);
        rotation.matrix[1][2] = (float) -sin(theta);
        rotation.matrix[2][1] = (float) sin(theta);
        rotation.matrix[2][2] = (float) cos(theta);
        return rotation;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < 4; i++) {
            sb.append(Arrays.toString(matrix[i]));
            if (i != 3) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
