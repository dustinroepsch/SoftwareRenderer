package com.dustinroepsch.SoftwareRenderer.Engine.Test;

import com.dustinroepsch.SoftwareRenderer.Engine.TransformationMatrix;
import com.dustinroepsch.SoftwareRenderer.Engine.Vector3;
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

    @Test
    public void rotateVectorXDirecection90Degrees() {
        TransformationMatrix rotation = TransformationMatrix.getXrotation((float) Math.toRadians(90));
        Vector3 toRotate = new Vector3(0, 0, 1);
        Vector3 expectedResult = new Vector3(0, -1, 0);
        Vector3 actualResult = rotation.applyTo(toRotate);
        assertTrue(expectedResult.toString() + " is not epsilonEqual to " + actualResult.toString(),expectedResult.epsilonEquals( actualResult, 0.0001f));
    }
}