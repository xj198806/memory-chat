package com.kakaluote.play.words;

import java.util.Date;

/**
 * Created by dwb on 2017/7/7.
 */
public class Words {
    private Long id;

    private Integer memberId;

    private Integer groupId;

    private String memberName;

    private String words;

    private Date speekTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public Date getSpeekTime() {
        return speekTime;
    }

    public void setSpeekTime(Date speekTime) {
        this.speekTime = speekTime;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
