package com.staranise.basic;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.staranise.thing.*;
import com.staranise.thing.controllers.CollideEventListener;
import com.staranise.thing.controllers.MovingController;
import com.staranise.thing.controllers.StopController;
import sun.security.ssl.Debug;

import java.util.List;

/**
 * Created by 현성 on 2016-04-10.
 * ball.
 */
public class BilliardBall extends QueObject {

    public float getHorizontalSpin() {
        return horizontalSpin;
    }

    public void setHorizontalSpin(float horizontalSpin) {
        this.horizontalSpin = horizontalSpin;
    }

    //negative : unClockwise , positive : clockwise
    private float horizontalSpin = 0;

    public BilliardBall(int num, Vec2 vecPos, World world, boolean bSagu){
        super(bSagu ? "sagu" + num + ".png" : "Ball" + num + ".png", 2.f, vecPos, world);
        _physEng.setShape(new Shape(GameConfig.BALL_RADIUS));

        setInputListener();
        setController();
        setCollideEventListener();
    }


    public void setCollideEventListener() {
        this.getEngine().collideEventListener = new CollideEventListener() {
            @Override
            public void collide(Thing opponent, Vec2 impact) {
                Vec2 spd = getEngine().getLinearSpeed(); // speed before collide

                float originHspin = horizontalSpin;

                if(opponent == null) { // collide with border
                    Vec2 directVec;
                    if (impact.x > 0) { //왼쪽벽과 충돌
                        directVec = new Vec2(0, -1);
                    } else if (impact.x < 0) { //오른쪽벽과 충돌
                        directVec = new Vec2(0, 1);
                    } else if (impact.y > 0) { //아래쪽 벽과 충돌
                        directVec = new Vec2(1, 0);
                    } else { //위쪽벽과 충돌
                        directVec = new Vec2(-1, 0);
                    }

                    //시계방향일때를 기준으로 방향을 정하고 시계 반대방향인 경우로 돌경우는 방향도 전부 반대일태니 이렇게함 제발되라 시발
                    directVec = (horizontalSpin > 0) ? directVec.multi(1) : directVec.multi(-1);

                    getEngine().addLinearSpeed(directVec.multi(GameConfig.HORIZONTAL_SPIN_FACTOR * Math.abs(horizontalSpin)));

//                    if(horizontalSpin > 0) {
//                        if (impact.x > 0) { //왼쪽벽과 충돌
//                            directVec = new Vec2(0, -1);
//                        } else if (impact.x < 0) { //오른쪽벽과 충돌
//                            directVec = new Vec2(0, 1);
//                        } else if (impact.y > 0) { //아래쪽 벽과 충돌
//                            directVec = new Vec2(1, 0);
//                        } else { //위쪽벽과 충돌
//                            directVec = new Vec2(-1, 0);
//                        }
//                    } else {
//                        if (impact.x > 0) { //왼쪽벽과 충돌
//
//                        } else if (impact.x < 0) { //오른쪽벽과 충돌
//
//                        } else if (impact.y > 0) { //아래쪽 벽과 충돌
//
//                        } else { //위쪽벽과 충돌
//
//                        }
//                    }

                } else {
                    List<BilliardBall> ballList = GameManager.getInstance().getBilliardBallList();
                    for(BilliardBall ball : ballList) {
                        if(ball.getEngine().getId().equals(opponent.getId())) {
                            ball.setHorizontalSpin(ball.getHorizontalSpin() - originHspin * 0.8f);
                        }
                    }
                }

                //if(horizontalSpin != 0) {
                    horizontalSpin += (horizontalSpin > 0) ?
                            -GameConfig.HORIZONTAL_SPIN_DECREASE_AMOUNT : GameConfig.HORIZONTAL_SPIN_DECREASE_AMOUNT;
                    if (originHspin * horizontalSpin < 0) //넘 많이 빼서 방향바뀐경우
                        horizontalSpin = 0;
                //}
            }
        };
    }

    public void setInputListener() {
        setTouchable(Touchable.enabled);
        InputListener listener = new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                if(Option.DEBUG_MODE) {
                    Debug.println("asd", "asd");
                    Stage stage = event.getTarget().getStage();
                    stage.addListener(new InputListener() {
                        @Override
                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                            return true;
                        }

                        @Override
                        public void touchDragged(InputEvent event, float x, float y, int pointer) {
                            super.touchDragged(event, x, y, pointer);
                        }

                        @Override
                        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                            Thing uni = BilliardBall.this.getEngine();
                            Vec2 pos = uni.getPosition();
                            Vec2 mouse = new Vec2(x, y);
                            uni.setLinearSpeed(mouse.minus(pos));
                            super.touchUp(event, x, y, pointer, button);
                            event.getStage().removeListener(this);
                        }
                    });
                }
                else {
                    final QueObject obj = (QueObject)event.getTarget();
                    final Cue cue = GameManager.getInstance().getCue();
                    cue.setVisible(true);
                    cue.setTargetBall((BilliardBall)obj);
                }
                super.touchUp(event, x, y, pointer, button);
            }
        };
        addListener(listener);
    }

    public void setController() {
        this.getEngine().setMovementController(new MovingController() {
            @Override
            public void control(Thing thing) {
                if(Option.DEBUG_MODE)
                    System.out.println("방향 전환 : " + thing.getId());

                thing.setAccComponent("마찰력", thing.getLinearSpeed().norm().opposite().multi(GameConfig.FRICTION_AMOUNT));

                if(thing.isComponentExist("수직스핀")) {
                    Vec2 verticalSpin = thing.getAccComponent("수직스핀");

                    thing.setAccComponent("수직스핀", verticalSpin.multi(GameConfig.VERTICAL_SPIN_DECREASE_FACTOR));
                }
            }
        });

        this.getEngine().setStopController(new StopController() {
            @Override
            public void control(Thing thing) {
                if(Option.DEBUG_MODE)
                    System.out.println("정지 : " + thing.getId());
                thing.resetDynamic();
            }
        });
    }

}
