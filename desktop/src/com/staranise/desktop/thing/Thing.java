package com.staranise.desktop.thing;

/**
 * Created by YuTack on 2016-04-05.
 *
 * Thing Engine
 *
 * class Thing, basic class of all object in engine
 *
 * kg, m, s
 */
public class Thing {
    public enum ThingType {
        Static, //zero speed
        Dynamic //normal one
    }

    private Vec2 position;
    private Vec2 linearSpeed;
    private Vec2 acceleration;
    private float mass;

    private ThingType type;

    //use it when search Thing from universe, it should provide proper equals method
    private String id;

    private Shape shape;
    //private float? angluarVelocty;

    public Thing() {}

    //dynamic Thing
    public Thing(float mass, Vec2 position, Vec2 linearSpeed, Vec2 acceleration) {
        this.mass = mass;
        this.position = position;
        this.linearSpeed = linearSpeed;
        this.acceleration = acceleration;
        this.type = ThingType.Dynamic;
    }

    //static Thing
    public Thing(Vec2 position) {
        this.position = position;
        this.type = ThingType.Static;
    }

    public void setCircleShape(float radius) {
        this.shape = new Shape(radius);
    }

    ////

    public Vec2 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vec2 acceleration) {
        this.acceleration = acceleration;
    }

    public Vec2 getLinearSpeed() {
        return linearSpeed;
    }

    public void setLinearSpeed(Vec2 linearSpeed) {
        this.linearSpeed = linearSpeed;
    }

    public Vec2 getPosition() {
        return position;
    }

    public void setPosition(Vec2 position) {
        this.position = position;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public ThingType getType() {
        return type;
    }

    public void setType(ThingType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
