package com.staranise;

import com.badlogic.gdx.Game;
import com.staranise.screen.GameMain;
import com.staranise.screen.Selection;

/**
 * Created by 현성 on 2016-04-11..
 */
public class TheBilliard extends Game {

    private Selection selection;

    @Override
    public void create(){
        selection = new Selection(this);
        setScreen(selection);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose(){
    }
}
