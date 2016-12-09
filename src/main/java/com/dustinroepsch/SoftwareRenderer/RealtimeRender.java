package com.dustinroepsch.SoftwareRenderer;

import com.dustinroepsch.SoftwareRenderer.Engine.Model.Face;
import com.dustinroepsch.SoftwareRenderer.Engine.Model.Mesh;
import com.dustinroepsch.SoftwareRenderer.Engine.TransformationMatrix;
import com.dustinroepsch.SoftwareRenderer.Engine.Vector3;
import com.dustinroepsch.SoftwareRenderer.Utilities.BufferedImageUtilities;
import processing.core.PApplet;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by dustin on 12/9/16.
 */
public class RealtimeRender extends PApplet {
    private Mesh mesh;
    TransformationMatrix rotation;
    @Override
    public void settings() {
        size(500,500);
    }

    @Override
    public void setup() {
        try {
            String home = System.getProperty("user.home");
            File inputObj = new File(home + "/african_head.obj");
            mesh = new Mesh(inputObj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw() {
        clear();
        background(0);
        rotation = TransformationMatrix.getXrotation((float) Math.toRadians(millis() / 500f));
        for (Face face : mesh.faces) {
            Vector3[] vertices = face.getVertices();
            for (int i = 0; i < vertices.length; i++) {
                vertices[i] = rotation.applyTo(vertices[i]);
            }
            for (Vector3 startVertice : vertices) {
                for (Vector3 endVertice : vertices) {
                    int x0 = (int) ((startVertice.getX()) * width / 2f + width / 2f);
                    int y0 = height - (int) ((startVertice.getY()) * width / 2f + height / 2f);
                    int x1 = (int) ((endVertice.getX()) * width/ 2f + width / 2f);
                    int y1 = height - (int) ((endVertice.getY()) * height / 2f + height/ 2f);
                    //BufferedImageUtilities.line(outputImage, x0, y0, x1, y1, Color.WHITE.getRGB());
                    stroke(255,255,255);
                    line(x0, y0, x1, y1);
                }
            }
        }
    }

    public static void main(String[] args) {
        PApplet.main("com.dustinroepsch.SoftwareRenderer.RealtimeRender");
    }
}
