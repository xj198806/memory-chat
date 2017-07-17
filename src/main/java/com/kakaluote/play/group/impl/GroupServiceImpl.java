package com.kakaluote.play.group.impl;

import com.kakaluote.play.group.Group;
import com.kakaluote.play.group.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dwb on 2017/7/10.
 */
public class GroupServiceImpl implements GroupService{
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public boolean addGroup(Group group) {
        logger.info("addGroup " + group.toString());
        return true;
    }

    @Override
    public boolean removeGroup(int groupId) {
        logger.info("removeGroup: " + String.valueOf(groupId));
        return true;
    }
}
