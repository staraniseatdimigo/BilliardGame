package com.staranise;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.staranise.Basic.BilliardBall;
import com.staranise.Basic.Cue;
import com.staranise.Basic.GameResource;
import com.staranise.Basic.World;
import com.staranise.thing.Vec2;

//
public class GameMain implements Screen {
    private BilliardBall _ball1;
    private BilliardBall _ball2;
	private Cue _cue;

	private World world1;

	public GameMain(){
	}

	@Override
	public void show () {
		world1 = new World(true, true);

		_ball1 = new BilliardBall(1, new Vec2(100.f, 100.f), world1);
		_ball2 = new BilliardBall(2, new Vec2(300.f, 300.f), world1);
		_cue = new Cue();
		_cue.setVisible(false);

		_ball1.getEngine().setLinearSpeed(new Vec2(0.f, 0.f));
		_ball2.getEngine().setLinearSpeed(new Vec2(0.f, 0.f));

		world1.AddObject(_cue);
		world1.AddObject(_ball1);
		world1.AddObject(_ball2);

		buttonInit();
	}

	public void buttonInit() {
		GameResource.getInstance().getDrawable("new_button");
		Button spinBtn;
		Button.ButtonStyle spinBtnStyle = new Button.ButtonStyle();

		spinBtnStyle.up = GameResource.getInstance().getDrawable("new_button");
		spinBtnStyle.down = GameResource.getInstance().getDrawable("new_button_pressed");

		spinBtn = new Button(spinBtnStyle);
		spinBtn.addListener( new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

			}
		});

		world1.AddObject(spinBtn);
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0.75f, 0.75f, 0.75f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world1.Render(delta);
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
