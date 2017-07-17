package com.kakaluote.play.support;

import com.kakaluote.play.group.Group;
import com.kakaluote.play.member.Member;
import com.kakaluote.play.words.Words;

import java.util.Collection;
import java.util.List;

/**
 * Created by dwb on 2017/7/10.
 */
public interface PlayFacade {
    boolean addGroup(Group group);
    boolean removeGroup(int groupId);

    boolean addMember(int groupId, Member member);
    boolean removeMember(int groupId, int memberId);

    boolean speak(int groupId, int memberId, String words);

    List<Member> getMembers(int groupId);
    Member getMember(int memberId);
    Collection<Group> getGroups();
    List<Words> getAllGroupWords(int groupId);
}
