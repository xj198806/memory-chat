package com.kakaluote.web.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by dwb on 2017/7/17.
 */
public class PlaySpeakWebEvent extends ApplicationEvent {
    private Integer groupId;
    public PlaySpeakWebEvent(Object source,Integer groupId) {
        super(source);
        this.groupId = groupId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
