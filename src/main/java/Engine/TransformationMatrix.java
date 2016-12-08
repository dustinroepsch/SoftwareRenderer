package Engine;

import Engine.Vector3;

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
     *
     * @param other The matrix to apply this matrix to.
     * @return The transformed matrix.
     */
    public TransformationMatrix applyTo(TransformationMatrix other) {
        float[][] result = new float[4][4];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                result[row][col] = this.matrix[row][col] * other.matrix[col][row];
            }
        }
        return new TransformationMatrix(result);
    }
}
