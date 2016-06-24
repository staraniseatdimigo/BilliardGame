package com.staranise.basic;

import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuTack on 2016-05-05..
 *
 * 게임 정보 저장, 관리, 모든 엑터 레퍼런스 저장
 */
public class GameManager {

    public enum GAME_STEP {
        MAIN_MENU,
        MENU_SELECT,
        MENU_1,
        MENU_2,
        MENU_N,//...
        INGAME
    }

    private GameManager() {
    }

    private List<BilliardBall> balls = new ArrayList<BilliardBall>();
    private World world;
    private Cue _cue;
    private float _fDeltaTime;

    private GAME_STEP _step = GAME_STEP.MAIN_MENU;

    public OrthographicCamera cam;
    private BallSpinDecider _decider;

    private final static GameManager instance = new GameManager();

    public static GameManager getInstance() {
        return instance;
    }

    public void setCue(Cue cue) {
        _cue = cue;
    }

    public void setSpinDecider(BallSpinDecider decider) {
        _decider = decider;
    }

    public void setDeltaTime(float fDeltaTime) {
        _fDeltaTime = fDeltaTime;
    }

    public float getDeltaTime() {
        return _fDeltaTime;
    }

    public List<BilliardBall> getBilliardBallList() {
        return balls;
    }

    public Cue getCue() {
        return _cue;
    }

    public BallSpinDecider getSpinDecider() {
        return _decider;
    }

    public void setGameStep(GAME_STEP step) {
        _step = step;
    }

    public GAME_STEP getGameStep() {
        return _step;
    }

    public void executeStep(){
    }

}
