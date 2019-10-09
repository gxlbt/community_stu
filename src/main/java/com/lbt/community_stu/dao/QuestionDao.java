package com.lbt.community_stu.dao;

import com.lbt.community_stu.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by lbt on 2019/9/19 9:22
 */

@Mapper
public interface QuestionDao {
    public void createQuestion(Question question);

    List<Question> list(@Param("page") Integer page, @Param("size") Integer size);

    Integer count();
}
