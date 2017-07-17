package com.kakaluote.web.event;

import com.kakaluote.play.support.PlayFacadeImpl;
import com.kakaluote.web.util.SseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;

/**
 * Created by dwb on 2017/7/17.
 */
@Component
public class PlaySpeakListener implements ApplicationListener<PlaySpeakWebEvent> {
    private final Logger LOGGER = LoggerFactory.getLogger(PlaySpeakListener.class);
    @Autowired
    private SseUtils sseUtils;
    @Override
    public void onApplicationEvent(PlaySpeakWebEvent playSpeakWebEvent) {
        int groupId = playSpeakWebEvent.getGroupId();
        for (SseEmitter sseEmitter : new ArrayList<>(sseUtils.getWordsEmitters())) {
            try {
                sseEmitter.send(PlayFacadeImpl.getInstance().getAllGroupWords(groupId), MediaType.APPLICATION_JSON);
            } catch (Exception ex) {
                LOGGER.debug("Error sending event to client ", ex);
            }
        }
    }
}
