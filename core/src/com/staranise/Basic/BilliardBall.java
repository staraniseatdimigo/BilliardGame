package com.staranise.Basic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.staranise.GameMain;
import com.staranise.thing.Shape;
import com.staranise.thing.Thing;
import com.staranise.thing.Vec2;
import sun.security.ssl.Debug;

/**
 * Created by 현성 on 2016-04-10..
 */
public class BilliardBall extends QueObject {

    public BilliardBall(int num, Vec2 vecPos, World world){
        super("Ball" + num + ".png", 2.f, vecPos, world);
        _physEng.setShape(new Shape(15.f));

        setTouchable(Touchable.enabled);
        InputListener listener = new InputListener()
        {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                GameMain._gameStep = 2;
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
                    World world = obj.getWorld();
                    final Cue cue = (Cue)(world.getStage().getActors().get(1));
                    cue.setVisible(true);
                    cue.setTargetBall((BilliardBall)obj);
                }
                super.touchUp(event, x, y, pointer, button);
            }
        };
        addListener(listener);
    }

}
