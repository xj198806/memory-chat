package com.kakaluote.play.member;

/**
 * Created by dwb on 2017/7/10.
 */
public interface MemberService {
    boolean addMember(Member member);
    boolean removeMember(int memberId);
    boolean memberSpeak(int memberId, String words);
}
