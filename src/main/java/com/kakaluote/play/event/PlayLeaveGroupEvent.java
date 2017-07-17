package com.kakaluote.play.event;

import com.kakaluote.play.PlayEvent;

/**
 * Created by dwb on 2017/7/7.
 */
public class PlayLeaveGroupEvent extends PlayEvent {
    private int groupId;
    private int memberId;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public PlayLeaveGroupEvent(Object source,int groupId,int memberId) {
        super(source);
        this.groupId = groupId;
        this.memberId = memberId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
