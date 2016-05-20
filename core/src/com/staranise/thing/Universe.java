package com.staranise.thing;

import com.staranise.Basic.GameManager;

import java.util.*;

/**
 * Created by YuTack on 2016-04-08.
 * 우주
 */
public class Universe {
    private List<Thing> things = new LinkedList<Thing>();
    private Vec2 gravity;

    public Universe() {}

    private Map<Thing, Boolean> eventChecker = new HashMap<Thing, Boolean>(); //갱신자 / true : 운동중, false : 중지 or 상태변화

    public Universe(Vec2 gravity) {
        this.gravity = gravity;
    }

    public void addThing(Thing thing) {
        assert(thing.getShape() != null);
        thing.setMovementController(new Controller() {
            @Override
            public void control(Thing thing) {
                System.out.println("방향 전환 : " + thing.getId());
                thing.addAccComponent("마찰력", thing.getLinearSpeed().norm().opposite().multi(thing.getMass() * 15.f));
                if(thing.isComponentExist("수직스핀")) {
                    Vec2 verticalSpin = thing.getAccComponent("수직스핀");
                    if(verticalSpin.getLength() < 1.f){
                        thing.deleteAccComponent("수직스핀");
                    }
                    else
                        thing.adjustAccComponent("수직스핀", verticalSpin.opposite().norm().multi(0.75f));
                }
            }
        });

        thing.setStopController(new Controller() {
            @Override
            public void control(Thing thing) {
                System.out.println("정지 : " + thing.getId());
                thing.resetDynamic();
            }
        });

        things.add(thing);
        eventChecker.put(thing, false);
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
        GameManager.getInstance().setDeltaTime(dt);
        for(int i=0; i<things.size(); i++) {

            Thing t1 = things.get(i);

            if(t1.getType() == Thing.ThingType.Dynamic) {
                Vec2 futureSpd = t1.getLinearSpeed().add(t1.getAcceleration().multi(dt));
                float fTargetVLen = t1.getLinearSpeed().getLength();
                if(fTargetVLen > 0.5f) {
                    t1.executeMovementController();
                    eventChecker.put(t1, true);
                }
                else if((fTargetVLen < 0.5f || Vec2.isOpposite(t1.getLinearSpeed(), futureSpd))){
                    t1.executeStopController();
                    eventChecker.put(t1, false);
                }
                futureSpd = t1.getLinearSpeed().add(t1.getAcceleration().multi(dt));
                t1.setLinearSpeed(futureSpd);
                t1.setPosition(t1.getPosition().add(t1.getLinearSpeed().multi(dt)));
            }

            for(int j= i+1; j<things.size(); j++) {
                //circle collision check
                Thing t2 = things.get(j);
                if(t1.getShape().getRadius() != -1 &&
                        t1.getShape().radius + things.get(j).getShape().radius >
                        t1.getPosition().getDistance(t2.getPosition())) {

                    //한 물체가 정지한 경우에만 적용됨 테스트 코드에선 움직이는 thing을 먼저 넣고 그다음 안움직이는걸 넣는 걸로
                    //t1이 기준 동체, t2가 대상 동체
                    Vec2 v0 = t1.getLinearSpeed().minus(t2.getLinearSpeed()); // 좌표계 변환 t1이 정지한 물체로
                    Vec2 v2Direct = t2.getPosition().minus(t1.getPosition()).norm(); // 부딪힌 물체의 방향벡터 두 물체의 중심 위치를 빼서 구함

                    float v0v2cos = v0.getCos(t2.getPosition().minus(t1.getPosition())); // 움직이는 물체의 방향과 부딪힌 물체와의 사이각

                    float v2Length = v0.multi(v0v2cos).getLength(); // v0의 크기와 사이각을 통해 v2에 길이를 구함

                    Vec2 v2 = v2Direct.multi(v2Length); //v2방향와 크기로 v2를 구함
                    Vec2 v1 = new Vec2(v0.x - v2.x, v0.y - v2.y); // v1 + v2 = v0을 이용해 v1을 구함

                    t1.setLinearSpeed(v1.add(t2.getLinearSpeed()));
                    t2.setLinearSpeed(v2.add(t2.getLinearSpeed()));
                    System.out.println("충돌 : " + t1.getId() + "//" + t2.getId());

                    eventChecker.put(t1, false);
                    eventChecker.put(t2, false);
                }
            }
        }
    }
}
