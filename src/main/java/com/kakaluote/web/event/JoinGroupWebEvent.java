package com.kakaluote.web.event;

import com.kakaluote.play.member.Member;
import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by dwb on 2017/7/17.
 */
public class JoinGroupWebEvent extends ApplicationEvent {
    private HttpServletResponse httpServletResponse;
    private Member member;
    public JoinGroupWebEvent(Object source,HttpServletResponse response,
                             Member member) {
        super(source);
        this.httpServletResponse = response;
        this.member = member;
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
