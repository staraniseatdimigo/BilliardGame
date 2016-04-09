package com.staranise.desktop.thing;

/**
 * Created by YuTack on 2016-04-05.
 */
public class Vec2 {
    public float x = 0;
    public float y = 0;

    //initial value (0,0)
    public Vec2() {}

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vec2(double x, double y) {
        this.x = (float)x;
        this.y = (float)y;
    }

    public Vec2(Vec2 other) {
        this.x = other.x;
        this.y = other.y;
    }

    //Multiple with real number
    public Vec2 multi(float num) {
        return new Vec2(x * num, y * num);
    }

    public Vec2 add(Vec2 other) {
        return new Vec2(x + other.x, y + other.y);
    }

    //사이각
    public float getCos(Vec2 other) {
        return (float)(inProduct(other) / (getLength() * other.getLength()));
    }

    public Vec2 opposite() {
        return new Vec2(-x, -y);
    }

    //inner Product
    public float inProduct(Vec2 other) {
        return (this.x * other.x) + (this.y * other.y);
    }

    public float getLength() {
        return (float)Math.sqrt(x*x + y*y);
    }

    public float getLength(Vec2 other) {
        return (float)Math.sqrt(Math.pow((x - other.x), 2) + Math.pow((y - other.y), 2));
    }

    public float getDistance(Vec2 other) {
        return (float)Math.sqrt(Math.pow(x-other.x, 2) + Math.pow(y-other.y, 2));
    }

    public Vec2 norm() {
        return new Vec2(x / getLength(), y / getLength());
    }

    @Override
    public String toString() {
        return "(" + Float.toString(x) + ", " + Float.toString(y) + ")";
    }
}
