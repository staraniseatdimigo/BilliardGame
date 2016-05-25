package com.staranise.thing;

import java.util.EventListener;

/**
 * Created by YuTack on 2016-05-25.
 * 자바 이벤트 리스너 쓸줄모름 ㅅㄱ
 */
public interface CollideEventListener {
    //opponent will be null when it collided with border
    //impact : myLaterSpeed - myOriginalSpeed
    void collide(Thing opponent, Vec2 impact);
}