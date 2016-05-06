package com.staranise.Basic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YuTack on 2016-05-05.
 *
 * 게임 정보 저장, 관리, 모든 엑터 레퍼런스 저장
 */
public class GameManager {
    private GameManager() {}

    private List<BilliardBall> balls = new ArrayList<BilliardBall>();
    private World world;
    private Cue _cue;

    private boolean isOnGame = false;

    private final static GameManager instance = new GameManager();

    public static GameManager getInstance() {
        return instance;
    }

    public void setCue(Cue cue){
        _cue = cue;
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
