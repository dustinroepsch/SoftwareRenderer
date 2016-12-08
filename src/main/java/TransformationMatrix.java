/**
 * Transformation Matrix as described in
 * ttp://www.codinglabs.net/article_world_view_projection_matrix.aspx
 *
 * @author Dustin Ryan-Roepsch
 */
public class TransformationMatrix {
    private float[][] matrix;

    public TransformationMatrix() {
        matrix = new float[4][4];
        //setup the identity matrix
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = i == j ? 1 : 0;
            }
        }
    }

    public void translate(float x, float y, float z) {
        matrix[0][3] += x;
        matrix[1][3] += y;
        matrix[2][3] += z;
    }

    public void rotateX(float theta) {

    }
}
