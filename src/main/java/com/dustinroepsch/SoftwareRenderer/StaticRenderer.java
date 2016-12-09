package com.dustinroepsch.SoftwareRenderer;

import com.dustinroepsch.SoftwareRenderer.Engine.Model.Face;
import com.dustinroepsch.SoftwareRenderer.Engine.Model.Mesh;
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
        //Mesh inputMesh = new Mesh(inputObj);
        BufferedImage outputImage = new BufferedImage(500, 500, BufferedImage.TYPE_3BYTE_BGR);
        BufferedImageUtilities.line(outputImage, 1, 1, 45, 30, Color.BLUE.getRGB());
        ImageIO.write(outputImage, "png", new File("out.png"));
    }
}
