package com.kakaluote.play.support;

import com.kakaluote.play.PlayContext;
import com.kakaluote.play.PlayEvent;
import com.kakaluote.play.PlayRuntimeContext;
import com.kakaluote.play.event.*;
import com.kakaluote.play.group.Group;
import com.kakaluote.play.group.GroupService;
import com.kakaluote.play.group.impl.GroupServiceImpl;
import com.kakaluote.play.member.Member;
import com.kakaluote.play.member.MemberService;
import com.kakaluote.play.member.impl.MemberServiceImpl;
import com.kakaluote.play.words.Words;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dwb on 2017/7/10.
 */
public class PlayFacadeImpl implements PlayFacade {

    private static final String source = "facade";

    private static GroupService groupService;

    private static MemberService memberService;

    private static PlayContext playContext;

    private static PlayEventPublisher playEventPublisher = new PlayEventPublisher() {
        @Override
        public void publish(PlayEvent playEvent) {
            Iterator<PlayEventListener> iterator = this.PlayEventListeners.iterator();
            while (iterator.hasNext()){
                PlayEventListener playEventListener = iterator.next();
                if (playEventListener.supportEvent(playEvent))
                    playEventListener.onPlayEvent(playEvent);
            }
        }
    };

    static {
        groupService = new GroupServiceImpl();
        memberService = new MemberServiceImpl();
        playContext = new PlayRuntimeContext();
        playContext.start();
    }

    private PlayFacadeImpl (){}

    static class PlayFacadeHolder{
        private static PlayFacadeImpl instance = new PlayFacadeImpl();
    }

    public static PlayFacadeImpl getInstance(){
        return PlayFacadeHolder.instance;
    }

    @Override
    public boolean addGroup(Group group) {
        group.setId(playContext.groupIdQueue.poll());
        boolean res = groupService.addGroup(group);
        if (res){
            playContext.onPlayEvent(new PlayAddGroupEvent(source,group));
        }
        return res;
    }

    @Override
    public boolean removeGroup(int groupId) {
        boolean res = groupService.removeGroup(groupId);
        if (res){
            playContext.onPlayEvent(new PlayRemoveGroupEvent(source,groupId));
        }
        return res;
    }

    @Override
    public boolean addMember(int groupId, Member member) {
        member.setId(playContext.memberIdQueue.poll());
        boolean res = memberService.addMember(member);
        if (res){
            playContext.onPlayEvent(new PlayJoinGroupEvent(source,member,groupId));
        }
        return res;
    }

    @Override
    public boolean removeMember(int groupId,int memberId) {
        boolean res = memberService.removeMember(memberId);
        if (res){
            playContext.onPlayEvent(new PlayLeaveGroupEvent(source,groupId,memberId));
        }
        return res;
    }

    @Override
    public boolean speak(int groupId,int memberId, String words) {
        boolean res = memberService.memberSpeak(memberId,words);
        if (res){
            Words words1 = new Words();
            words1.setGroupId(groupId);
            words1.setMemberId(memberId);
            words1.setWords(words);
            words1.setSpeekTime(new Date());
            playContext.onPlayEvent(new PlaySpeakEvent(source,groupId,memberId,words1));
        }
        return res;
    }

    @Override
    public List<Member> getMembers(int groupId) {
        return playContext.groupMemberMap.get(groupId);
    }

    @Override
    public Member getMember(int memberId){
        return playContext.memberMap.get(memberId);
    }

    @Override
    public Collection<Group> getGroups() {
        Collection<Group> groups = playContext.groupMap.values();
        return groups;
    }


    @Override
    public List<Words> getAllGroupWords(int groupId) {
        return playContext.groupListWordsMap.get(groupId);
    }
}
