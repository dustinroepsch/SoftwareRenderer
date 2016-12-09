package com.dustinroepsch.SoftwareRenderer.Engine.Model;

/**
 * A triangular face in a model;
 */
public class Face {
    private Vertice[] vertices;

    public Face(Vertice a, Vertice b, Vertice c) {
        vertices = new Vertice[3];
        vertices[0] = a;
        vertices[1] = b;
        vertices[2] = c;
    }

    public Vertice[] getVertices() {
        return vertices;
    }
}
