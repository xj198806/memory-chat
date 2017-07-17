package com.kakaluote.play;

import com.kakaluote.play.event.*;
import com.kakaluote.play.group.Group;
import com.kakaluote.play.member.Member;
import com.kakaluote.play.words.Words;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Created by dwb on 2017/7/7.
 */
public class PlayRuntimeContext implements  PlayContext{

    Vector<PlayEventListener> PlayEventListeners = new Vector<>();

    @Override
    public boolean start() {
        for(int i=1; i<= 10; i++){
            groupIdQueue.add(i);
        }
        for(int i=1; i<= 100; i++){
            memberIdQueue.add(i);
        }
        int groupId1 = groupIdQueue.poll();
        int groupId2 = groupIdQueue.poll();
        groupMap.put(groupId1,new Group(groupId1,"default"));
        groupMap.put(groupId2,new Group(groupId2,"test"));
        PlayEventListeners.add(new PlayEventListener() {
            @Override
            public boolean supportEvent(PlayEvent playEvent) {
                return playEvent instanceof PlayAddGroupEvent;
            }

            @Override
            public void onPlayEvent(PlayEvent event) {
                groupMap.put(((PlayAddGroupEvent)event).getGroup().getId(),
                        ((PlayAddGroupEvent)event).getGroup());
            }
        });
        PlayEventListeners.add(new PlayEventListener() {
            @Override
            public boolean supportEvent(PlayEvent playEvent) {
                return playEvent instanceof PlayRemoveGroupEvent;
            }

            @Override
            public void onPlayEvent(PlayEvent event) {
                groupMap.remove (((PlayRemoveGroupEvent)event).getGroupId());
            }
        });
        PlayEventListeners.add(new PlayEventListener() {
            @Override
            public boolean supportEvent(PlayEvent playEvent) {
                return playEvent instanceof PlayJoinGroupEvent;
            }

            @Override
            public void onPlayEvent(PlayEvent event) {
                PlayJoinGroupEvent playJoinGroupEvent = ((PlayJoinGroupEvent)event);
                List<Member> members = groupMemberMap.get(playJoinGroupEvent.getGroupId());
                if (members == null)
                    members = new ArrayList<>();
                members.add(playJoinGroupEvent.getMember());
                memberMap.put(playJoinGroupEvent.getMember().getId(),playJoinGroupEvent.getMember());
                groupMemberMap.put(((PlayJoinGroupEvent)event).getGroupId(),
                        members);
                Group group = groupMap.get(((PlayJoinGroupEvent)event).getGroupId());
                group.setMembers(members);
                groupMap.put(((PlayJoinGroupEvent)event).getGroupId(),group);
            }
        });
        PlayEventListeners.add(new PlayEventListener() {
            @Override
            public boolean supportEvent(PlayEvent playEvent) {
                return playEvent instanceof PlayLeaveGroupEvent;
            }

            @Override
            public void onPlayEvent(PlayEvent event) {
                PlayLeaveGroupEvent playLeaveGroupEvent = (PlayLeaveGroupEvent)event;
                List<Member> members = groupMemberMap.get(playLeaveGroupEvent.getGroupId());
                Member member = memberMap.get(playLeaveGroupEvent.getMemberId());
                memberMap.remove(member.getId());
                members.remove(member);
                groupMemberMap.put(playLeaveGroupEvent.getGroupId(),members);

                Group group = groupMap.get(playLeaveGroupEvent.getGroupId());
                group.setMembers(members);
                groupMap.put(playLeaveGroupEvent.getGroupId(),group);
            }
        });
        PlayEventListeners.add(new PlayEventListener() {
            @Override
            public boolean supportEvent(PlayEvent playEvent) {
                return playEvent instanceof PlaySpeakEvent;
            }

            @Override
            public void onPlayEvent(PlayEvent event) {
                PlaySpeakEvent playSpeakEvent = (PlaySpeakEvent) event;
                List<Words> wordsList = groupListWordsMap.get(playSpeakEvent.getGroupId());
                if (wordsList == null){
                    wordsList = new ArrayList<>();
                }
                Words words = playSpeakEvent.getWords();
                words.setMemberName(memberMap.get(playSpeakEvent.getMemberId()).getName());
                wordsList.add(words);
                groupListWordsMap.put(playSpeakEvent.getGroupId(),wordsList);
            }
        });
        return true;
    }

    @Override
    public boolean stop() {
        groupMemberMap.clear();
        groupMap.clear();
        groupListWordsMap.clear();
        return true;
    }

    @Override
    public boolean supportEvent(PlayEvent playEvent) {
        return false;
    }

    @Override
    public void onPlayEvent(PlayEvent event) {
        Iterator<PlayEventListener> iterator = this.PlayEventListeners.iterator();
        while (iterator.hasNext()){
            PlayEventListener playEventListener = iterator.next();
            if (playEventListener.supportEvent(event))
                playEventListener.onPlayEvent(event);
        }
    }
}
