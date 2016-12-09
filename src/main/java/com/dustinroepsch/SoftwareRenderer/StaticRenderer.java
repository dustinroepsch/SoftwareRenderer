package com.dustinroepsch.SoftwareRenderer;

import com.dustinroepsch.SoftwareRenderer.Engine.Model.Face;
import com.dustinroepsch.SoftwareRenderer.Engine.Model.Mesh;
import com.dustinroepsch.SoftwareRenderer.Engine.Model.Vertice;
import com.dustinroepsch.SoftwareRenderer.Engine.Vector3;
import com.dustinroepsch.SoftwareRenderer.Utilities.BufferedImageUtilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class uses the com.dustinroepsch.SoftwareRenderer.Engine to output a single rendered image.
 *
 * @author Dustin Ryan-Roepsch
 */
public class StaticRenderer {
    public static void main(String[] args) throws IOException {
        String home = System.getProperty("user.home");
        File inputObj = new File(home + "/african_head.obj");
        Mesh inputMesh = new Mesh(inputObj);
        BufferedImage outputImage = new BufferedImage(500, 500, BufferedImage.TYPE_3BYTE_BGR);

        for (Face face : inputMesh.faces) {
            Vertice[] vertices = face.getVertices();
            for (Vertice startVertice : vertices) {
                for (Vertice endVertice : vertices) {
                    int x0 = (int) ((startVertice.x) * outputImage.getWidth() / 2f + outputImage.getWidth() / 2f);
                    int y0 = outputImage.getHeight() - (int) ((startVertice.y) * outputImage.getHeight() / 2f + outputImage.getHeight() / 2f);
                    int x1 = (int) ((endVertice.x) * outputImage.getWidth() / 2f + outputImage.getWidth() / 2f);
                    int y1 = outputImage.getHeight() - (int) ((endVertice.y) * outputImage.getHeight() / 2f + outputImage.getHeight() / 2f);
                    BufferedImageUtilities.line(outputImage, x0, y0, x1, y1, Color.WHITE.getRGB());
                }
            }
        }
        ImageIO.write(outputImage, "png", new File("out.png"));
    }
}
