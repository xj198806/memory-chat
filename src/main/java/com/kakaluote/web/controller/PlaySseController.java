package com.kakaluote.web.controller;

import com.kakaluote.play.support.PlayFacadeImpl;
import com.kakaluote.web.event.PlaySpeakListener;
import com.kakaluote.web.util.SseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;

/**
 * Created by dwb on 2017/7/11.
 */
@RestController
@RequestMapping("/play")
public class PlaySseController {

    private final Logger LOGGER = LoggerFactory.getLogger(PlaySpeakListener.class);
    @Autowired
    private SseUtils sseUtils;

    @RequestMapping(value="/push/words",produces="text/event-stream") //①
    public @ResponseBody
    SseEmitter words() {
        final SseEmitter emitter = new SseEmitter();
        emitter.onCompletion(new Runnable() {
            @Override
            public void run() {
                sseUtils.getWordsEmitters().remove(emitter);
            }
        });
        sseUtils.getWordsEmitters().add(emitter);

        for (SseEmitter sseEmitter : new ArrayList<>(sseUtils.getWordsEmitters())) {
            try {
                sseEmitter.send(PlayFacadeImpl.getInstance().getAllGroupWords(1), MediaType.APPLICATION_JSON);
            } catch (Exception ex) {
                LOGGER.debug("Error sending event to client ", ex);
            }
        }
        return emitter;
    }

    @RequestMapping(value="/push/groups",produces="text/event-stream") //①
    public @ResponseBody
    SseEmitter groups() {
        final SseEmitter emitter = new SseEmitter();
        emitter.onCompletion(new Runnable() {
            @Override
            public void run() {
                sseUtils.getGroupEmitters().remove(emitter);
            }
        });
        sseUtils.getGroupEmitters().add(emitter);
        for (SseEmitter sseEmitter : new ArrayList<>(sseUtils.getGroupEmitters())) {
            try {
                sseEmitter.send(PlayFacadeImpl.getInstance().getMembers(1), MediaType.APPLICATION_JSON);
            } catch (Exception ex) {
                LOGGER.debug("Error sending event to client ", ex);
            }
        }
        return emitter;
    }

}
