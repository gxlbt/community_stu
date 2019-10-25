package com.lbt.community_stu.service;

import com.lbt.community_stu.dao.UserMapper;
import com.lbt.community_stu.model.User;
import com.lbt.community_stu.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lbt on 2019/10/24 16:49
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        //User dbUser = userDao.findUserByAccountId(user.getAccountId());
        if (users.size() == 0){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //更新
            //User dbUser = users.get(0);
            User upUser = new User();
            upUser.setName(user.getName());
            upUser.setAvatarUrl(user.getAvatarUrl());
            upUser.setGmtModified(System.currentTimeMillis());
            upUser.setToken(user.getToken());
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(users.get(0).getId());
            userMapper.updateByExampleSelective(upUser,example);
            //userDao.update(dbUser);
        }
    }
}
