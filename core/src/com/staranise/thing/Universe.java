package com.staranise.thing;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by YuTack on 2016-04-08.
 */
public class Universe {
    private LinkedList<Thing> things;
    private Vec2 gravity;

    public void Universe() {}

    public void Universe(Vec2 gravity) {
        this.gravity = gravity;
    }

    public void addThing(Thing thing) {
        things.add(thing);
    }

    public boolean removeThing(Thing thing) {
        return things.remove(thing);
    }

    public void removeThing(Object id) {
        for(Thing t : things) {
            if(t.equals(id)) things.remove(things);
        }
    }

    //하스스톤 하지마라 박유택

    //return null if failed
    public Thing findThing(Object id) {
        for(Thing t : things) {
            if(t.equals(id)) return t;
        }
        return null;
    }

    public void flow(float dt) {
        for(int i=0; i<things.size(); i++) {

            Thing t1 = things.get(i);

            if(t1.getType() == Thing.ThingType.Dynamic) {
                t1.setLinearSpeed(t1.getLinearSpeed().add(t1.getAcceleration().multi(dt)));
                t1.setPosition(t1.getLinearSpeed().multi(dt));
            }

            for(int j= i+1; j<things.size(); j++) {
                //circle collision check
                Thing t2 = things.get(j);
                if(t1.getShape().getRadius() != -1 &&
                        t1.getShape().radius + things.get(j).getShape().radius >
                        t1.getPosition().getLength(t2.getPosition())) {

                }
            }
        }
    }
}
