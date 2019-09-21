package com.lbt.community_stu.model;

import lombok.Data;

/**
 * Created by lbt on 2019/9/20 10:18
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
}
