package com.staranise;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.staranise.Basic.*;
import com.staranise.thing.Vec2;

//
public class GameMain implements Screen {

	private World world1;
	private Cue _cue;

	public GameMain(){
	}

	@Override
	public void show () {

		BilliardBoard _board;
		BilliardBall _ball1;
		BilliardBall _ball2;
		final BallSpinDecider _decider;
		final BallSpinPoint _point;

		world1 = new World(true, true);

		_board = new BilliardBoard();
		_ball1 = new BilliardBall(1, new Vec2(100.f, 100.f), world1);
		_ball2 = new BilliardBall(2, new Vec2(300.f, 300.f), world1);

		_decider = new BallSpinDecider();
		_point = new BallSpinPoint();

		_cue = new Cue();

		_ball1.getEngine().setLinearSpeed(new Vec2(0.f, 0.f));
		_ball2.getEngine().setLinearSpeed(new Vec2(0.f, 0.f));

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
				if(_decider.isVisible()) {
					_decider.setVisible(false);
					_point.setVisible(false);
				}
				else
					_decider.setVisible(true);
			}
		});

		world1.AddObject(_board);
		world1.AddObject(_cue);
		world1.AddObject(_decider);
		world1.AddObject(_point);
		world1.AddObject(spinBtn);
		world1.AddObject(_ball1);
		world1.AddObject(_ball2);
	}

	private Vec2 _oldDir = null;

	private void cueEvent(){
		float curX = Gdx.input.getX();
		float curY = Gdx.graphics.getHeight() - Gdx.input.getY();
		if(!Gdx.input.isTouched()) _oldDir = null;

		if(_cue.getTargetBallPos() != null) {
			Vec2 origin = new Vec2(0.f, -1.f);
			Vec2 direction = new Vec2(curX, curY);
			Vec2 newdir = direction.minus(_cue.getTargetBallPos());
			if(Gdx.input.isTouched()){
				if(_oldDir == null)
					_oldDir = newdir;
				_cue.addScrolling(newdir.getLength() - _oldDir.getLength());
				_oldDir = new Vec2(newdir);
			}
			float cosvalue = origin.getCos(newdir);
			_cue.setAngle((float) Math.acos(cosvalue) * (newdir.x >= 0 ? 1.f : -1.f));
		}
	}

	@Override
	public void render (float delta) {
		cueEvent();
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
