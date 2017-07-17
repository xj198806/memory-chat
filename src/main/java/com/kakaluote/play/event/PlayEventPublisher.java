package com.kakaluote.play.event;

import com.kakaluote.play.PlayEvent;

import java.util.Vector;

/**
 * Created by dwb on 2017/7/7.
 */
public interface PlayEventPublisher {
    Vector<PlayEventListener> PlayEventListeners = new Vector<>();

    void publish(PlayEvent playEvent);
}
