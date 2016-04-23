package com.staranise.thing;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

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
        assert(thing.getShape() != null);
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
                t1.setLinearSpeed(t1.getLinearSpeed().add(t1.getAcceleration().multi(dt)));
                t1.setPosition(t1.getPosition().add(t1.getLinearSpeed().multi(dt)));
            }

            for(int j= i+1; j<things.size(); j++) {
                //circle collision check
                Thing t2 = things.get(j);
                if(t1.getShape().getRadius() != -1 &&
                        t1.getShape().radius + things.get(j).getShape().radius >
                        t1.getPosition().getLength(t2.getPosition())) {

                    //한 물체가 정지한 경우에만 적용됨 테스트 코드에선 움직이는 thing을 먼저 넣고 그다음 안움직이는걸 넣는 걸로
                    //t1이 처음 움직이던 물체, t2가 멈춰있던 물체
                    Vec2 v0 = t1.getLinearSpeed(); // 움직이는 물체 처음속도
                    Vec2 v2Direct = t2.getPosition().minus(t1.getPosition()).norm(); // 부딪힌 물체의 방향벡터 두 물체의 중심 위치를 빼서 구함

                    float v0v2cos = v0.getCos(t2.getPosition().minus(t1.getPosition())); // 움직이는 물체의 방향과 부딪힌 물체와의 사이각
                    System.out.println("------------------------------------------" + v0v2cos);

                    float v2Length = v0.multi(v0v2cos).getLength(); // v0의 크기와 사이각을 통해 v2에 길이를 구함
                    System.out.println("------------------------------------------" + v2Length);

                    Vec2 v2 = v2Direct.multi(v2Length); //v2방향와 크기로 v2를 구함
                    Vec2 v1 = new Vec2(v0.x - v2.x, v0.y - v2.y); // v1 + v2 = v0을 이용해 v1을 구함

                    t1.setLinearSpeed(v1);
                    System.out.println("------------------------------------------" + v1);
                    t2.setLinearSpeed(v2);
                    System.out.println("------------------------------------------" + v2);
                }
            }
        }
    }
}
