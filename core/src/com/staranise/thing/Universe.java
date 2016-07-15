package com.staranise.thing;

import com.staranise.Basic.GameManager;
import com.staranise.Basic.Option;

import java.util.*;
import java.util.List;

/**
 * Created by YuTack on 2016-04-08.
 * 우주
 */
public class Universe {
    private List<Thing> things = new LinkedList<Thing>();
    private Vec2 gravity;

    private Vec2 borderOrigin; //left bottom
    private Vec2 borderSize;
    // true : delete object reached border, false : reflect object
    private boolean borderOption = false;

    public Universe() {}

    private Map<Thing, Boolean> eventChecker = new HashMap<Thing, Boolean>(); //갱신자 / true : 운동중, false : 중지 or 상태변화

    public Universe(Vec2 gravity) {
        this.gravity = gravity;
    }

    //반사 사각형
    //option true : delete object reached border, false : reflect object
    public void setBorder(Vec2 borderOrigin, Vec2 borderSize, boolean borderOption) {
        this.borderOrigin = borderOrigin;
        this.borderSize = borderSize;
        this.borderOption = borderOption;
    }

    public void addThing(Thing thing) {
        assert(thing.getShape() != null);

        things.add(thing);
        eventChecker.put(thing, false);
    }

    public List<Thing> getThings() {
        return things;
    }

    public boolean removeThing(Thing thing) {
        return things.remove(thing);
    }

    public void removeThing(Object id) {
        for (Thing t : things) {
            if (t.equals(id)) things.remove(t);
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

            // 민준
            // x, y 범위값은 내가 보이는대로 대충 파란 영역 안으로 설정함 - 정확하게 바꾸는건 알아서.
            // x 범위 : 100.0 ~ 540.0

            if (t1.getPosition().x <= borderOrigin.x || t1.getPosition().x >= borderOrigin.x + borderSize.x) {
                // 내가 코딩을 못해서 일단 좀 드럽게 구현하는데 바꾸려면 바꾸셈
                if (!borderOption) {
                    //collid event에서 스피드 값을 더할경우를 고려해 가장 자연스러운 상황에 맞게 만듬
                    Vec2 originSpd = t1.getLinearSpeed();
                    Vec2 resultSpd = new Vec2(-t1.getLinearSpeed().x, t1.getLinearSpeed().y);
                    t1.collideEventListener.collide(null, resultSpd.minus(t1.getLinearSpeed()));
                    t1.addLinearSpeed(resultSpd.minus(originSpd));
                } else
                    removeThing(t1);
            }

            // y 범위 : 110.0 ~ 330.0
            if (t1.getPosition().y <= borderOrigin.y || t1.getPosition().y >= borderOrigin.y + borderSize.y) {
                if (!borderOption) {
                    Vec2 originSpd = t1.getLinearSpeed();
                    Vec2 resultSpd = new Vec2(t1.getLinearSpeed().x, -t1.getLinearSpeed().y);
                    t1.collideEventListener.collide(null, resultSpd.minus(t1.getLinearSpeed()));
                    t1.addLinearSpeed(resultSpd.minus(originSpd));
                } else
                    removeThing(t1);
            }


            if (t1.getType() == Thing.ThingType.Dynamic) {
                Vec2 futureSpd = t1.getLinearSpeed().add(t1.getAcceleration().multi(dt));
                float fTargetVLen = t1.getLinearSpeed().getLength();
                if (fTargetVLen > 0.5f) {
                    t1.executeMovementController();
                    eventChecker.put(t1, true);
                } else if ((fTargetVLen < 0.5f || Vec2.isOpposite(t1.getLinearSpeed(), futureSpd))) {
                    t1.executeStopController();
                    eventChecker.put(t1, false);
                }
                futureSpd = t1.getLinearSpeed().add(t1.getAcceleration().multi(dt));
                t1.setLinearSpeed(futureSpd);
                t1.setPosition(t1.getPosition().add(t1.getLinearSpeed().multi(dt)));
            }

            for (int j = i + 1; j < things.size(); j++) {
                //circle collision check
                Thing t2 = things.get(j);
                if (t1.getShape().getRadius() != -1 &&
                        t1.getShape().radius + things.get(j).getShape().radius >
                                t1.getPosition().getDistance(t2.getPosition())) {

                    //t1이 기준 동체, t2가 대상 동체
                    Vec2 t1OriginSpd = t1.getLinearSpeed();
                    Vec2 t2OriginSpd = t2.getLinearSpeed();

                    Vec2 v0 = t1.getLinearSpeed().minus(t2.getLinearSpeed()); // 좌표계 변환 t1이 정지한 물체로
                    Vec2 v2Direct = t2.getPosition().minus(t1.getPosition()).norm(); // 부딪힌 물체의 방향벡터 두 물체의 중심 위치를 빼서 구함

                    float v0v2cos = v0.getCos(t2.getPosition().minus(t1.getPosition())); // 움직이는 물체의 방향과 부딪힌 물체와의 사이각

                    float v2Length = v0.multi(v0v2cos).getLength(); // v0의 크기와 사이각을 통해 v2에 길이를 구함

                    Vec2 v2 = v2Direct.multi(v2Length); //v2방향와 크기로 v2를 구함
                    Vec2 v1 = new Vec2(v0.x - v2.x, v0.y - v2.y); // v1 + v2 = v0을 이용해 v1을 구함

                    Vec2 t1ResultSpd = v1.add(t2OriginSpd);
                    Vec2 t2ResultSpd = v2.add(t2OriginSpd);

                    t1.collideEventListener.collide(t2, t1ResultSpd.minus(t1.getLinearSpeed()));
                    t2.collideEventListener.collide(t1, t2ResultSpd.minus(t2.getLinearSpeed()));

                    t1.addLinearSpeed(t1ResultSpd.minus(t1OriginSpd));
                    t2.addLinearSpeed(t2ResultSpd.minus(t2OriginSpd));

                    if (Option.DEBUG_MODE)
                        System.out.println("충돌 : " + t1.getId() + "//" + t2.getId());

                    eventChecker.put(t1, false);
                    eventChecker.put(t2, false);
                }
            }
        }
    }
}
