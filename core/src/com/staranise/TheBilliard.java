package com.staranise;

import com.badlogic.gdx.Game;

/**
 * Created by 현성 on 2016-04-11.
 */
public class TheBilliard extends Game {

    private GameMain gameMain;

    @Override
    public void create(){
        gameMain = new GameMain();
        setScreen(gameMain);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose(){
    }
}
