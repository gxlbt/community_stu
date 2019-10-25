package com.lbt.community_stu.dao;

import com.lbt.community_stu.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by lbt on 2019/9/19 9:22
 */

@Mapper
public interface UserDao {
    void insert(User user);

    User findUserByToken(String token);

    User findUserById(Integer creator);

    User findUserByAccountId(String accountId);

    void update(User user);
}
