package com.kakaluote.play.member;

import com.kakaluote.play.words.Words;

import java.util.List;

/**
 * Created by dwb on 2017/7/7.
 */
public class Member {

    private Integer id;

    private Integer groupId;

    private String name;

    private List<Words> wordsList;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", groupId=" + groupId +
                ", name='" + name + '\'' +
                ", wordsList=" + wordsList +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public List<Words> getWordsList() {
        return wordsList;
    }

    public void setWordsList(List<Words> wordsList) {
        this.wordsList = wordsList;
    }
}
