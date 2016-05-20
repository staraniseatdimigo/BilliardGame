package com.staranise.thing;

import com.badlogic.gdx.Gdx;
import com.staranise.Basic.GameManager;

import java.util.*;

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

    private Map<String, Vec2> accComps = new HashMap<String, Vec2>();
    protected Vec2 position = new Vec2(0,0);
    protected Vec2 linearSpeed = new Vec2(0,0);
    protected Vec2 acceleration = new Vec2(0,0);
    protected float mass;
    protected Controller movementController = null;
    protected Controller stopController = null;

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

    public void setMovementController(Controller controller){
        movementController = controller;
    }

    public void setStopController(Controller controller){
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

    public void addAccComponent(String compName, Vec2 accComp){
        //acceleration.setLength(0.f);
        if(accComps.containsKey(compName))
            acceleration = acceleration.minus(accComps.get(compName));
        accComps.put(compName, accComp);
        /*for(Map.Entry<String, Vec2> comp : accComps.entrySet()) {
            acceleration = acceleration.add(comp.getValue());
        }*/
        acceleration = acceleration.add(accComp);
    }

    public final Vec2 getAccComponent(String compName){
        return accComps.get(compName);
    }

    public void adjustAccComponent(String compName, Vec2 diff){
        accComps.put(compName, accComps.get(compName).add(diff));
        acceleration = acceleration.add(diff);
    }

    public void deleteAccComponent(String compName){
        Vec2 comp = accComps.get(compName);
        if(comp != null) {
            acceleration = acceleration.minus(comp);
            accComps.remove(compName);
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

    public boolean temp = false;
    public void setLinearSpeed(Vec2 linearSpeed) {
        this.linearSpeed = linearSpeed;
    }

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
