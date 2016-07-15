package com.staranise.thing;

import com.staranise.thing.controllers.CollideEventListener;
import com.staranise.thing.controllers.MovingController;
import com.staranise.thing.controllers.StopController;

import java.util.*;

/**
 * Created by YuTack on 2016-04-05.
 *
 * Thing Engine
 *
 * class Thing, Basic class of all object in engine
 *
 * kg, m, s.
 */
public class Thing {
    public enum ThingType {
        Static, //zero speed
        Dynamic //normal one
    }

    private Map<String, Vec2> accComps = new HashMap<String, Vec2>();
    protected Vec2 position = new Vec2(0,0);
    protected Vec2 linearSpeed = new Vec2(0,0);
    protected Vec2 acceleration = new Vec2(0,0);
    protected float mass;
    protected MovingController movementController = null;
    protected StopController stopController = null;

    public CollideEventListener collideEventListener = null;

    private ThingType type;

    //use it when search Thing from universe, it should provide proper equals method
    private String id;

    private Shape shape;
    //private float? angluarVelocty;

    public Thing() {}

    public Thing(float mass, Vec2 position){
        this.mass = mass;
        this.position = position;
        this.type = ThingType.Dynamic;
    }

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

    public void setMovementController(MovingController controller){
        movementController = controller;
    }

    public void setStopController(StopController controller){
        stopController = controller;
    }

    public void executeMovementController(){
        if(movementController != null)
            movementController.control(this);
    }

    public void executeStopController(){
        if(stopController != null)
            stopController.control(this);
    }

    //return zero vector instead of null
    public final Vec2 getAccComponent(String compName){
        if(accComps.get(compName) == null)
            return new Vec2(0,0);
        else
            return accComps.get(compName);
    }

    //it will automacally remove componetn if newAcc is zero vector or not so meaningful (length is less than 0.1f)
    public void setAccComponent(String compName, Vec2 newAcc) {

        if(accComps.containsKey(compName))
            acceleration = acceleration.minus(accComps.get(compName));

        if(newAcc.getLength() <= 0.1f) {
            accComps.remove(compName);
        }
        else {
            accComps.put(compName, newAcc);
            acceleration = acceleration.add(newAcc);
        }
    }

    public boolean isComponentExist(String compName){
        return accComps.containsKey(compName);
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
    public void addLinearSpeed(Vec2 linearSpeed) { this.linearSpeed = this.linearSpeed.add(linearSpeed); }

    public void resetDynamic(){
        linearSpeed.setLength(0.f);
        acceleration.setLength(0.f);
        accComps.clear();
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
