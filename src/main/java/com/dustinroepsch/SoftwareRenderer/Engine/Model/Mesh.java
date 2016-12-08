package com.dustinroepsch.SoftwareRenderer.Engine.Model;

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
    private ArrayList<Face> faces;

    /**
     * Creates a Mesh from a Wavefront Obj file.
     *
     * @param file The Wavefront Obj file.
     * @throws FileNotFoundException File must be readable
     */
    public Mesh(File file) throws FileNotFoundException {
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

        ArrayList<Vertice> verts = new ArrayList<Vertice>();

        while (fileScanner.hasNextLine()) {
            String currentLine = fileScanner.nextLine();
            Matcher verticeMatcher = verticePattern.matcher(currentLine);
            if (verticeMatcher.matches()) {
                verts.add(new Vertice(
                        Float.valueOf(verticeMatcher.group(1)),
                        Float.valueOf(verticeMatcher.group(2)),
                        Float.valueOf(verticeMatcher.group(3))
                ));
            }
            Matcher faceMatcher = facePattern.matcher(currentLine);
            if (faceMatcher.matches()) {
                faces.add(
                        new Face(
                                verts.get(Integer.parseInt(faceMatcher.group(1)) - 1), //-1 because our array is zero indexed, but this reference is 1-indexed.
                                verts.get(Integer.parseInt(faceMatcher.group(4)) - 1),
                                verts.get(Integer.parseInt(faceMatcher.group(7)) - 1)
                        )
                );
            }
        }
    }
}
