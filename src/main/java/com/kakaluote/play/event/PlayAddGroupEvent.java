package com.kakaluote.play.event;

import com.kakaluote.play.PlayEvent;
import com.kakaluote.play.group.Group;

/**
 * Created by dwb on 2017/7/10.
 */
public class PlayAddGroupEvent extends PlayEvent {

    private Group group;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public PlayAddGroupEvent(Object source,Group group) {
        super(source);
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
