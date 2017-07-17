package com.kakaluote.play.member.impl;

import com.kakaluote.play.member.Member;
import com.kakaluote.play.member.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dwb on 2017/7/10.
 */
public class MemberServiceImpl implements MemberService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean addMember(Member member) {
        logger.info("addMember " + member.toString());
        return true;
    }

    @Override
    public boolean removeMember(int memberId) {
        logger.info("removeMember " + String.valueOf(memberId));
        return true;
    }

    @Override
    public boolean memberSpeak(int memberId, String words) {
        logger.info("memberSpeak " + memberId+ ": " + words);
        return true;
    }
}
