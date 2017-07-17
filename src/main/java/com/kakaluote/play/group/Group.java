package com.kakaluote.play.group;

import com.kakaluote.play.member.Member;

import java.util.List;

/**
 * Created by dwb on 2017/7/7.
 */
public class Group {
    private Integer id;

    private String name;

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", members=" + members +
                '}';
    }

    public Group(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private List<Member> members;

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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
