package com.lbt.community_stu.mapper;

import com.lbt.community_stu.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by lbt on 2019/9/19 9:22
 */

@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values(#{name},#{accountId}," +
            "#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
}
