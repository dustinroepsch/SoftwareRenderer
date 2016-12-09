package com.dustinroepsch.SoftwareRenderer.Engine.Model;

import com.dustinroepsch.SoftwareRenderer.Engine.Vector3;

/**
 * A representation of a point in space
 */
public class Vertice {
    public float x;
    public float y;
    private float z;

    public Vertice(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
