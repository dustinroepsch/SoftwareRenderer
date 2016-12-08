package Engine.Test;

import Engine.TransformationMatrix;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dustin on 12/8/16.
 */
public class TransformationMatrixTest {
    @Test
    public void applyIdentity() {
        TransformationMatrix identity = new TransformationMatrix();
        TransformationMatrix random = new TransformationMatrix();
        random.randomize();
        TransformationMatrix result = identity.applyTo(random);
        assertEquals(random, result);
    }
}