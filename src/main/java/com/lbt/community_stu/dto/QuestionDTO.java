package com.lbt.community_stu.dto;

import com.lbt.community_stu.model.User;
import lombok.Data;

/**
 * Created by lbt on 2019/9/21 18:17
 */
@Data
public class QuestionDTO {
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
    private User user;
}
