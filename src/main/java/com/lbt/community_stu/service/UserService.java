package com.lbt.community_stu.service;

import com.lbt.community_stu.dao.UserDao;
import com.lbt.community_stu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lbt on 2019/10/24 16:49
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public void createOrUpdate(User user) {
        User dbUser = userDao.findUserByAccountId(user.getAccountId());
        if (dbUser == null){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userDao.insert(user);
        }else {
            //更新
            dbUser.setName(user.getName());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setToken(user.getToken());
            userDao.update(dbUser);
        }
    }
}
