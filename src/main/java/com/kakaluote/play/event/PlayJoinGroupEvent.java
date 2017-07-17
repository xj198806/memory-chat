package com.kakaluote.play.event;

import com.kakaluote.play.PlayEvent;
import com.kakaluote.play.member.Member;

/**
 * Created by dwb on 2017/7/7.
 */
public class PlayJoinGroupEvent extends PlayEvent {
    private Member member;
    private int groupId;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public PlayJoinGroupEvent(Object source,Member member,int groupId) {
        super(source);
        this.member = member;
        this.groupId = groupId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
