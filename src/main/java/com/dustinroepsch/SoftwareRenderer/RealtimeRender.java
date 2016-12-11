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
        size(900,900);
        //fullScreen();
    }

    @Override
    public void setup() {
        frameRate(20);
        try {
            String home = System.getProperty("user.home");
            File inputObj = new File(home + "/diablo.obj");
            mesh = new Mesh(inputObj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    float angle = PI/2;
    @Override
    public void draw() {
        clear();
        background(0);
        //angle = angle + 0.01f;
        rotation = TransformationMatrix.getXrotation(angle);
        for (Face face : mesh.faces) {
            Vector3[] vertices = face.getVertices();
            for (int i = 0; i < vertices.length; i++) {
                vertices[i] = rotation.applyTo(vertices[i]);
            }
            for (Vector3 startVertice : vertices) {
                for (Vector3 endVertice : vertices) {
                    float x0 = map(startVertice.getX(), mesh.smallestX, mesh.largestX, 0, width);
                    float x1 = map(endVertice.getX(), mesh.smallestX, mesh.largestX, 0, width);
                    float y0 = map(startVertice.getY(), mesh.smallestY, mesh.largestY, 0, height);
                    float y1 = map(endVertice.getY(), mesh.smallestY, mesh.largestY, 0, height);
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
