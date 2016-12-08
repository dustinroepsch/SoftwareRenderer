package com.dustinroepsch.SoftwareRenderer;

import com.dustinroepsch.SoftwareRenderer.Engine.Model.Mesh;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class uses the com.dustinroepsch.SoftwareRenderer.Engine to output a single rendered image.
 *
 * @author Dustin Ryan-Roepsch
 */
public class StaticRenderer {
    public static void main(String[] args) throws FileNotFoundException {
        String home = System.getProperty("user.home");
        File inputObj = new File(home + "/african_head.obj");
        Mesh inputMesh = new Mesh(inputObj);
    }
}
