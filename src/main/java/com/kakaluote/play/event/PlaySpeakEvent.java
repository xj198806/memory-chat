package com.kakaluote.play.event;

import com.kakaluote.play.PlayEvent;
import com.kakaluote.play.words.Words;

/**
 * Created by dwb on 2017/7/7.
 */
public class PlaySpeakEvent extends PlayEvent {
    private int groupId;
    private int memberId;
    private Words words;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public PlaySpeakEvent(Object source,int groupId,int memberId,Words words) {
        super(source);
        this.groupId = groupId;
        this.memberId = memberId;
        this.words = words;
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

    public Words getWords() {
        return words;
    }

    public void setWords(Words words) {
        this.words = words;
    }
}
