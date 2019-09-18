package com.lbt.community_stu.community_stu.dto;

import lombok.Data;

/**
 * Created by lbt on 2019/9/18 15:39
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
