package com.kakaluote.web.util;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Collection;
import java.util.LinkedList;

import static java.util.Collections.synchronizedCollection;

/**
 * Created by dwb on 2017/7/17.
 */
@Component
public class SseUtils {
    private final Collection<SseEmitter> groupEmitters = synchronizedCollection(
            new LinkedList<SseEmitter>());
    private final Collection<SseEmitter> wordsEmitters = synchronizedCollection(
            new LinkedList<SseEmitter>());

    public Collection<SseEmitter> getGroupEmitters() {
        return groupEmitters;
    }

    public Collection<SseEmitter> getWordsEmitters() {
        return wordsEmitters;
    }
}
