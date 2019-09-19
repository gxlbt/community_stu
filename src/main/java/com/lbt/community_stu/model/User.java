package com.lbt.community_stu.model;

import lombok.Data;

/**
 * Created by lbt on 2019/9/19 9:22
 */
@Data
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
}
