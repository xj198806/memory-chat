package com.kakaluote.play;

import com.kakaluote.play.event.PlayEventListener;
import com.kakaluote.play.group.Group;
import com.kakaluote.play.member.Member;
import com.kakaluote.play.words.Words;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dwb on 2017/7/7.
 */
public interface PlayContext extends PlayLifeCircle ,PlayEventListener {
    Queue<Integer> groupIdQueue = new LinkedList<>();
    Queue<Integer> memberIdQueue = new LinkedList<>();

    /**
     * 成员id对应name map
     */
    public static Map<Integer,String> memberNameMap = new ConcurrentHashMap<>();

    /**
     * 组group id对应组成员 map
     */
    Map<Integer,List<Member>> groupMemberMap = new ConcurrentHashMap<>();

    /**
     * 组group map
     */
    Map<Integer,Group> groupMap = new ConcurrentHashMap<>();

    /**
     * member map
     */
    Map<Integer,Member> memberMap = new ConcurrentHashMap<>();

    /**
     * 组id对应 所有话语map
     */
    Map<Integer,BlockingDeque<Words>> groupQueueWordsMap = new ConcurrentHashMap<>();

    Map<Integer,List<Words>> groupListWordsMap = new ConcurrentHashMap<>();
}
