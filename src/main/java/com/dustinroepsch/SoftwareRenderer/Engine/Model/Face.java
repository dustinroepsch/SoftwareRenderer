package com.dustinroepsch.SoftwareRenderer.Engine.Model;

/**
 * A triangular face in a model;
 */
public class Face {
    private Vertice a, b, c;

    public Face(Vertice a, Vertice b, Vertice c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}