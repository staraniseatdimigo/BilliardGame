package com.staranise.basic;

import com.badlogic.gdx.Screen;

/**
 * Created by cradle on 2016. 6. 17..
 */
public class GameScreen implements Screen {

    public GameScreen(GameManager.GAME_STEP step){
        GameManager.getInstance().setGameStep(step);
    }

    @Override
    public void show() {

        GameManager.getInstance().executeStep();

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
