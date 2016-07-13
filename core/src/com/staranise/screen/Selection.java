package com.staranise.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.staranise.basic.GameResource;
import com.staranise.basic.World;

/**
 * Created by 현성 on 2016-07-13.
 */
public class Selection implements Screen {

    private World _world;
    private Game _game;

    public Selection(Game game){
        _game = game;
    }

    @Override
    public void show() {
        Skin initialized = GameResource.getInstance().getSkin();
        TextButton backBtn = new TextButton("BACK", initialized, "default");
        ImageButton sagu = new ImageButton(initialized, "default");
        ImageButton samgu = new ImageButton(initialized, "samgu");

        backBtn.setWidth(202);
        backBtn.setHeight(50);

        sagu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                _game.setScreen(new GameMain(4));
            }
        });

        samgu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                _game.setScreen(new GameMain(3));
            }
        });

        sagu.setX(512.f - 354.f - 46.f);
        sagu.setY(384.f - 135.5f);
        samgu.setX(512.f + 46.f);
        samgu.setY(384.f - 135.5f);
        backBtn.setX(512.f - 101.f);
        backBtn.setY(150.f);

        _world = new World(false, true);

        _world.AddObject(backBtn);
        _world.AddObject(sagu);
        _world.AddObject(samgu);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        _world.Render(delta);
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
