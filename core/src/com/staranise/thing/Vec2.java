package com.staranise.thing;

/**
 * Created by YuTack on 2016-04-05..
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

    public static Vec2 multi(Vec2 v1, float num) { return new Vec2(v1.x * num, v1.y * num); }

    public Vec2 add(Vec2 other) {
        return new Vec2(x + other.x, y + other.y);
    }

    public static Vec2 add(Vec2 v1, Vec2 v2){
        return new Vec2(v1.x + v2.x, v1.y + v2.y);
    }

    public Vec2 minus(Vec2 other) { return new Vec2(x - other.x, y - other.y); }

    public static Vec2 minus(Vec2 v1, Vec2 v2) { return new Vec2(v1.x - v2.x, v1.x - v2.y); }

    //사이각
    public float getCos(Vec2 other) {
        return (dot(other) / (getLength() * other.getLength()));
    }

    public static float getCos(Vec2 v1, Vec2 v2)  {
        return (dot(v1, v2) / (v1.getLength() * v2.getLength()));
    }

    public Vec2 opposite() {
        return new Vec2(-x, -y);
    }

    //inner Product
    public float dot(Vec2 other) {
        return (this.x * other.x) + (this.y * other.y);
    }

    public static float dot(Vec2 v1, Vec2 v2) {
        return (v1.x * v2.x) + (v1.y * v2.y);
    }

    public float getLength() {
        return (float)Math.sqrt(x*x + y*y);
    }

    public float getDistance(Vec2 other) {
        return (float)Math.sqrt(Math.pow(x-other.x, 2) + Math.pow(y-other.y, 2));
    }

    public Vec2 norm() {
        if(getLength() == 0)
            return new Vec2(0.f, 0.f);
        return new Vec2(x / getLength(), y / getLength());
    }

    public void setLength(float fLength){
        float fCurLength = getLength();
        if(fLength == 0.f){
            x = 0.f;
            y = 0.f;
        }
        else {
            x *= fLength / fCurLength;
            y *= fLength / fCurLength;
        }
    }

    public void addLength(float delta){
        float fNewLength = getLength() + delta;
        setLength(fNewLength);
    }

    @Override
    public String toString() {
        return "(" + Float.toString(x) + ", " + Float.toString(y) + ")";
    }

    public static boolean isDirectionSame(Vec2 v1, Vec2 v2){
        Vec2 v1Dir = v1.norm();
        Vec2 v2Dir = v2.norm();
        if(v1Dir.x * v2Dir.x >= 0 && v1Dir.y * v2Dir.y >= 0){
            return Math.abs(v1Dir.x - v2Dir.x) < 0.1f && Math.abs(v1Dir.y - v2Dir.y) < 0.1f;
        }
        return false;
    }

    public static boolean isOpposite(Vec2 v1, Vec2 v2){
        if(v1.getLength() == 0.f || v2.getLength() == 0.f)
            return false;
        return isDirectionSame(v1, v2.opposite());
    }
}
