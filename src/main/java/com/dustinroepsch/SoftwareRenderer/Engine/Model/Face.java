package com.dustinroepsch.SoftwareRenderer.Engine.Model;

import com.dustinroepsch.SoftwareRenderer.Engine.Vector3;

/**
 * A triangular face in a model;
 */
public class Face {
    private Vector3[] vertices;

    /**
     * Constructs a face from three vertices
     * @param a a vertice
     * @param b a vertice
     * @param c a vertice
     */
    public Face(Vector3 a, Vector3 b, Vector3 c) {
        vertices = new Vector3[3];
        vertices[0] = a;
        vertices[1] = b;
        vertices[2] = c;
    }

    public Vector3[] getVertices() {
        return vertices;
    }
}
