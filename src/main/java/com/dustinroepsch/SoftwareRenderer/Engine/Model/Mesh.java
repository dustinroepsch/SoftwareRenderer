package com.dustinroepsch.SoftwareRenderer.Engine.Model;

import com.dustinroepsch.SoftwareRenderer.Engine.Vector3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Mesh is a 3D model.
 */
public class Mesh {
    public ArrayList<Face> faces;
    public float largestX;
    public float smallestX;
    public float largestY;
    public float smallestY;
    float largestZ;
    float smallestZ;

    /**
     * Creates a Mesh from a Wavefront Obj file.
     *
     * @param file The Wavefront Obj file.
     * @throws FileNotFoundException File must be readable
     */
    public Mesh(File file) throws FileNotFoundException {
        float largestX = 0, smallestX = 0, largestY = 0, smallestY = 0, largestZ = 0, smallestZ = 0;
        boolean setInitialBoundaryValues = false;
        faces = new ArrayList<Face>();
        Scanner fileScanner = new Scanner(file);
        //matches the pattern v 0.608654 -0.568839 -0.416318
        //puts each float into a capture group.
        //allows for exponents
        Pattern verticePattern =
                Pattern.compile("v\\s*([-+]?[0-9]*\\.?[0-9]+(?:[eE][-+]?[0-9]+)?)\\s*([-+]?[0-9]*\\.?[0-9]+(?:[eE][-+]?[0-9]+)?)\\s*([-+]?[0-9]*\\.?[0-9]+(?:[eE][-+]?[0-9]+)?)");
        //matches the pattern f 1193/1240/1193 1180/1227/1180 1179/1226/1179
        //puts each number into a capture group
        Pattern facePattern = Pattern.compile("f\\s*(\\d*)\\/(\\d*)\\/(\\d*)\\s*(\\d*)\\/(\\d*)\\/(\\d*)\\s*(\\d*)\\/(\\d*)\\/(\\d*)");
        ArrayList<Vector3> verts = new ArrayList<Vector3>();
        while (fileScanner.hasNextLine()) {
            String currentLine = fileScanner.nextLine();
            Matcher verticeMatcher = verticePattern.matcher(currentLine);
            if (verticeMatcher.matches()) {
                float x = Float.valueOf(verticeMatcher.group(1));
                float y = Float.valueOf(verticeMatcher.group(2));
                float z = Float.valueOf(verticeMatcher.group(3));
                if (!setInitialBoundaryValues) {
                    setInitialBoundaryValues = true;
                    largestX = smallestX = x;
                    largestY = smallestY = y;
                    largestZ = smallestZ = z;
                } else {
                    largestX = Math.max(largestX, x);
                    smallestX = Math.min(smallestX, x);
                    largestY = Math.max(largestY, y);
                    smallestY = Math.min(smallestY, y);
                    largestZ = Math.max(largestZ, z);
                    smallestZ = Math.min(smallestZ, z);
                }
                verts.add(new Vector3(x, y, z));
            }
            Matcher faceMatcher = facePattern.matcher(currentLine);
            if (faceMatcher.matches()) {
                faces.add(
                        new Face(
                                //we subtract 1 because our array is zero indexed, but this reference is 1-indexed.
                                verts.get(Integer.parseInt(faceMatcher.group(1)) - 1),
                                verts.get(Integer.parseInt(faceMatcher.group(4)) - 1),
                                verts.get(Integer.parseInt(faceMatcher.group(7)) - 1)
                        )
                );
            }
        }
        this.largestX = largestX;
        this.smallestX = smallestX;
        this.largestY = largestY;
        this.smallestY = smallestY;
        this.largestZ = largestZ;
        this.smallestZ = smallestZ;
    }
}
