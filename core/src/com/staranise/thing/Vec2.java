package com.staranise.thing;

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
    public static Vec2 multi(Vec2 src, float num) {
        src.x *= num;
        src.y *= num;
        return src;
    }

    public static Vec2 add(Vec2 src, Vec2 dest) {
        src.x += dest.x;
        src.y += dest.y;
        return src;
    }

    public static Vec2 minus(Vec2 src, Vec2 dest) {
        src.x -= dest.x;
        src.y -= dest.y;
        return src;
    }

    //사이각
    public float getCos(Vec2 other) {
        return (float)(Vec2.dot(this, other) / (getLength() * other.getLength()));
    }

    public Vec2 opposite() {
        this.x *= -1;
        this.y *= -1;
        return this;
    }

    //inner Product
    public static float dot(Vec2 v1, Vec2 v2) {
        return (v1.x * v2.x) + (v1.y * v2.y);
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

    public static Vec2 normalize(Vec2 src) {
        float length = src.getLength();
        Vec2 ret = new Vec2(src.x / length, src.y / length);
        return ret;
    }

    @Override
    public String toString() {
        return "(" + Float.toString(x) + ", " + Float.toString(y) + ")";
    }
}
