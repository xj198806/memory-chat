package com.kakaluote.web.event;

import com.kakaluote.play.member.Member;
import com.kakaluote.play.support.PlayFacadeImpl;
import com.kakaluote.web.util.SseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by dwb on 2017/7/17.
 */
@Component
public class JoinGroupListener implements ApplicationListener<JoinGroupWebEvent> {
    private final Logger LOGGER = LoggerFactory.getLogger(PlaySpeakListener.class);
    @Autowired
    private SseUtils sseUtils;

    @Override
    public void onApplicationEvent(JoinGroupWebEvent joinGroupWebEvent) {
        Member member = joinGroupWebEvent.getMember();
        HttpServletResponse httpServletResponse = joinGroupWebEvent.getHttpServletResponse();
        Cookie cookie = new Cookie("user",member.getId().toString());
        cookie.setMaxAge(46800);
        //设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
        cookie.setPath("/");
        Cookie cookie2 = new Cookie("userName",member.getName().toString());
        cookie2.setMaxAge(46800);
        //设置路径，这个路径即该工程下都可以访问该cookie 如果不设置路径，那么只有设置该cookie路径及其子路径可以访问
        cookie2.setPath("/");
        httpServletResponse.addCookie(cookie);
        httpServletResponse.addCookie(cookie2);

        for (SseEmitter sseEmitter : new ArrayList<>(sseUtils.getGroupEmitters())) {
            try {
                sseEmitter.send(PlayFacadeImpl.getInstance().getMembers(member.getGroupId()), MediaType.APPLICATION_JSON);
            } catch (Exception ex) {
                LOGGER.debug("Error sending event to client ", ex);
            }
        }
    }
}
