package com.staranise.thing;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by YuTack on 2016-04-08.
 * 우주
 */
public class Universe {
    private LinkedList<Thing> things = new LinkedList<Thing>();
    private Vec2 gravity;

    public Universe() {}

    public Universe(Vec2 gravity) {
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
            if(t.equals(id)) things.remove(t);
        }
    }

    //return null if failed
    public Thing findThing(Object id) {
        for(Thing t : things) {
            if(t.equals(id)) return t;
        }
        return null;
    }

    //정상화

    public void flow(float dt) {
        for(int i=0; i<things.size(); i++) {

            Thing t1 = things.get(i);

            if(t1.getType() == Thing.ThingType.Dynamic) {
                //t1.setLinearSpeed(t1.getLinearSpeed().add(t1.getAcceleration().multi(dt)));
                //t1.setPosition(t1.getLinearSpeed().multi(dt));
            }

            for(int j= i+1; j<things.size(); j++) {
                //circle collision check
                Thing t2 = things.get(j);
                if(t1.getShape().getRadius() != -1 &&
                        t1.getShape().radius + things.get(j).getShape().radius >
                        t1.getPosition().getLength(t2.getPosition())) {
                    //한 물체가 정지한 경우에만 적용됨 테스트 코드에선 움직이는 thing을 먼저 넣고 그다음 안움직이는걸 넣는 걸로
                    Vec2 v0 = t1.getLinearSpeed();

                    //v0와 v1방향(두 원 위치를 빼서 구함)으로 사이각 구한 후, v0에 구한 사이각(cos) v1 전체를 구함.
                    Vec2 v1 = v0.multi(v0.getCos(t2.getPosition().minus(t1.getPosition())));
                    Vec2 v2 = new Vec2(v0.x - v1.x, -v1.y);

                    t1.setLinearSpeed(v1);
                    t2.setLinearSpeed(v2);
                }
            }
        }
    }
}
