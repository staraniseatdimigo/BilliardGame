package com.staranise;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.staranise.Basic.ObjectManager;
import com.staranise.Basic.QueObject;

public class CueGameClass extends ApplicationAdapter {
	
	@Override
	public void create () {
        QueObject ball1 = new QueObject("./core/assets/Ball1.png", 50.f, 0.f);
        QueObject ball2 = new QueObject("./core/assets/Ball2.png", 60.f, 50.f);
        QueObject ball3 = new QueObject("./core/assets/Ball3.png", 88.f, 30.f);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.75f, 0.75f, 0.75f, 1.f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ObjectManager.getInstance().Render();
    }
}
