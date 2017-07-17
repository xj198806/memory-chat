package com.kakaluote.play.event;

import com.kakaluote.play.PlayEvent;

/**
 * Created by dwb on 2017/7/10.
 */
public class PlayRemoveGroupEvent extends PlayEvent {
    private int groupId;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public PlayRemoveGroupEvent(Object source,int groupId) {
        super(source);
        this.groupId = groupId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
