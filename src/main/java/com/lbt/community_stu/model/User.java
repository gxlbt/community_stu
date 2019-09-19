package com.lbt.community_stu.model;

import lombok.Data;

/**
 * Created by lbt on 2019/9/19 9:22
 */
@Data
public class User {
    private Integer id;
    private String accountId;   //账号
    private String name;        //用户名
    private String token;       //口令
    private Long gmtCreate;     //创建时间
    private Long gmtModified;   //修改时间
    private String bio;     //说明
}
