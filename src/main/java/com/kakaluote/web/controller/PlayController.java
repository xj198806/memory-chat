package com.kakaluote.web.controller;

import com.kakaluote.play.group.Group;
import com.kakaluote.play.member.Member;
import com.kakaluote.play.support.PlayFacadeImpl;
import com.kakaluote.web.event.JoinGroupWebEvent;
import com.kakaluote.web.event.PlayEventPublisher;
import com.kakaluote.web.event.PlaySpeakWebEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dwb on 2017/7/10.
 */
@RestController
@RequestMapping("/play")
public class PlayController {

    @Autowired
    private PlayEventPublisher playEventPublisher;

    @RequestMapping(value = "/addGroup",method = RequestMethod.POST)
    public boolean addGroup(@RequestBody Group group){
        return PlayFacadeImpl.getInstance().addGroup(group);
    }

    @RequestMapping(value = "/removeGroup/{groupId}",method = RequestMethod.PUT)
    public boolean removeGroup(@PathVariable int groupId){
        return PlayFacadeImpl.getInstance().removeGroup(groupId);
    }

    @RequestMapping(value = "/joinGroup/{groupId}",method = RequestMethod.POST)
    public boolean joinGroup(HttpServletResponse httpServletResponse,
            @PathVariable int groupId, @RequestBody Member member){
        boolean res = PlayFacadeImpl.getInstance().addMember(groupId,member);
        if (res){
            playEventPublisher.publish(new JoinGroupWebEvent(this,httpServletResponse,member));
        }
        return res;
    }

    @RequestMapping(value = "/leaveGroup/{groupId}/{memberId}",method = RequestMethod.PUT)
    public boolean leaveGroup(@PathVariable int groupId,@PathVariable int memberId){
        return PlayFacadeImpl.getInstance().removeMember(groupId,memberId);
    }

    @RequestMapping(value = "/speak/{groupId}/{memberId}",method = RequestMethod.POST)
    public boolean speak(HttpServletRequest request, HttpServletResponse response,
                         @PathVariable int groupId,@PathVariable int memberId,
                         String message){
        boolean res = PlayFacadeImpl.getInstance().speak(groupId,memberId,message);
        if (res){
            playEventPublisher.publish(new PlaySpeakWebEvent(this,groupId));
        }
        return res;
    }

}
