package com.kakaluote.play;

import java.util.EventObject;

/**
 * Created by dwb on 2017/7/7.
 */
public abstract class PlayEvent extends EventObject{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public PlayEvent(Object source) {
        super(source);
    }
}
