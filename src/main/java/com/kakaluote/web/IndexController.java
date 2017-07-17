package com.kakaluote.web;

import com.kakaluote.play.support.PlayFacadeImpl;
import com.kakaluote.web.event.PlaySpeakListener;
import com.kakaluote.web.util.SseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;

/**
 * Created by dwb on 2017/5/18.
 */
@Controller
public class IndexController {


    @RequestMapping("/")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "hello controller");

        return "index";
    }

}