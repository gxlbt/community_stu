package com.lbt.community_stu.mapper;

import com.lbt.community_stu.model.Question;
import com.lbt.community_stu.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by lbt on 2019/9/19 9:22
 */

@Mapper
public interface QuestionMapper {
    @Insert("insert into question " +
            "(title,description,creator,comment_count,view_count,like_count,tag,gmt_create,gmt_modified) " +
            "values(#{title},#{description},#{creator},#{commentCount},#{viewCount},#{likeCount},#{tag}," +
            "#{gmtCreate},#{gmtModified})")
    public void createQuestion(Question question);

    @Select("select * from question")
    List<Question> list();
}
