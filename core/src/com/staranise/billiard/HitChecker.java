package com.staranise.billiard;

import com.badlogic.gdx.Gdx;
import com.staranise.basic.BilliardBall;

/**
 * Created by YuTack on 2016-07-15.
 *
 * 공이 두개 피해서 잘 맞았는지 쓰리쿠션인지 등등 판단 공치는 순간 생기고 공이 멈추면 뒤짐
 */
public class HitChecker {
    private static HitChecker checker = null;

    public static void makeHitChecker(BilliardBall hero) {
        checker = new HitChecker();
        checker.hero = hero;
    }

    public static HitChecker getHitChecker() {
        return checker;
    }

    private BilliardBall hero; // 주인공 공

    public String getHeroId() {
        return hero.getEngine().getId();
    }

    private int wallHitCount = 0;
    private boolean isHitOpponent = false; // 치면 감점
    private boolean isHitBall1 = false;
    private boolean isHitBall2 = false;

    //모든 공의 속력이 0 이되면 호출시킴
    public static void notifyHitOvered() {
        GameManager gm = GameManager.getInstance();
        if(checker.isHitOpponent || (!checker.isHitBall1 && !checker.isHitBall2)) gm.notifyHitBad();
        else if(checker.isHitBall1 && checker.isHitBall2) {
            if(checker.wallHitCount >= 3)
                gm.notifyHitSuccess(true);
            else
                gm.notifyHitSuccess(false);
        } else {
            gm.notifyHitMiss();
        }

        checker = null;
    }

    public void notifyHeroHit(String opponentId) {
        if(opponentId == "wall")
            wallHitCount++;
        else if(opponentId == "player1" || opponentId == "player2")
            isHitOpponent = true;
        else if(opponentId == "ball1")
            isHitBall1 = true;
        else if(opponentId == "ball2")
            isHitBall2 = true;
    }

    public void log() {
        Gdx.app.log("HitChekcer", wallHitCount + ", " + isHitOpponent + ", " + isHitBall1 + ", " + isHitBall2);
    }
}
