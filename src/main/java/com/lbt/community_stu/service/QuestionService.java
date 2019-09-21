package com.lbt.community_stu.service;

import com.lbt.community_stu.dto.QuestionDTO;
import com.lbt.community_stu.mapper.QuestionMapper;
import com.lbt.community_stu.mapper.UserMapper;
import com.lbt.community_stu.model.Question;
import com.lbt.community_stu.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lbt on 2019/9/21 18:16
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list() {
        List<Question> questionList = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        if (questionList != null && questionList.size() > 0) {
            for (Question question : questionList) {
                QuestionDTO questionDTO = new QuestionDTO();
                User user = userMapper.findUserById(question.getCreator());
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
        }
        return questionDTOList;
    }
}
