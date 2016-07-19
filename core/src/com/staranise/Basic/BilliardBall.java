package com.staranise.basic;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.staranise.billiard.GameManager;
import com.staranise.billiard.HitChecker;
import com.staranise.thing.*;
import com.staranise.thing.controllers.CollideEventListener;
import com.staranise.thing.controllers.MovingController;
import com.staranise.thing.controllers.StopController;
import com.staranise.thing.shapes.Rect;
import sun.security.ssl.Debug;

/**
 * Created by 현성 on 2016-04-10.
 * ball.
 */
public class BilliardBall extends QueObject {

    //hit ball when valid player's turn
    private boolean isActive = false;

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public BilliardBall(int num, Vec2 vecPos, GNPBatchProcessor GNPBatchProcessor, boolean bSagu){
        super(bSagu ? "sagu" + num + ".png" : "Ball" + num + ".png", GNPBatchProcessor);
        _physEng = new Ball(vecPos, new Vec2(), GameConfig.BALL_RADIUS);

        setInputListener();
        setController();
        setCollideEventListener();
    }

    private void setCollideEventListener() {
        this.getEngine().collideEventListener = new CollideEventListener() {
            @Override
            public void collide(Thing opponent, Vec2 impact) {

                if(HitChecker.getHitChecker() != null && getEngine().getId().equals(HitChecker.getHitChecker().getHeroId())) {
                    if(Border.class.isInstance(opponent))
                        HitChecker.getHitChecker().notifyHeroHit("wall");
                    else
                        HitChecker.getHitChecker().notifyHeroHit(opponent.getId());
                }
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
                else if(isActive){
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
