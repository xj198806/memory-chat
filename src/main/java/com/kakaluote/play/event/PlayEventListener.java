package com.kakaluote.play.event;

import com.kakaluote.play.PlayEvent;

import java.util.EventListener;

/**
 * Created by dwb on 2017/7/7.
 */
public interface PlayEventListener<E extends PlayEvent> extends EventListener {

    boolean supportEvent(PlayEvent playEvent);
    void onPlayEvent(E event);
}
