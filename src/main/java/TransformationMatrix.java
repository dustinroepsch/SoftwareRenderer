/**
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
}
