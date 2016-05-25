package com.staranise.Basic;

import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuTack on 2016-05-05..
 *
 * 게임 정보 저장, 관리, 모든 엑터 레퍼런스 저장
 */
public class GameManager {
    private GameManager() {}

    private List<BilliardBall> balls = new ArrayList<BilliardBall>();
    private World world;
    private Cue _cue;
    private float _fDeltaTime;

    public OrthographicCamera cam;
    public BallSpinDecider decider;

    private boolean isOnGame = false;

    private final static GameManager instance = new GameManager();

    public static GameManager getInstance() {
        return instance;
    }

    public void setCue(Cue cue){
        _cue = cue;
    }

    public void setDeltaTime(float fDeltaTime){
        _fDeltaTime = fDeltaTime;
    }

    public float getDeltaTime(){
        return _fDeltaTime;
    }

    public List<BilliardBall> getBilliardBallList() {
        return balls;
    }

    public Cue getCue(){
        return _cue;
    }

    //game initialize
    public void gameEntered() {
        assert(!isOnGame);

        if(!isOnGame) {

        }
    }

}
