package com.lbt.community_stu.service;

import com.lbt.community_stu.dto.PaginationDTO;
import com.lbt.community_stu.dto.QuestionDTO;
import com.lbt.community_stu.dao.QuestionDao;
import com.lbt.community_stu.dao.UserDao;
import com.lbt.community_stu.model.Question;
import com.lbt.community_stu.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lbt on 2019/9/21 18:16
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private UserDao userDao;

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = questionDao.count();
        if (totalCount % size == 0){
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1){
            page = 1;
        }
        if (page > totalPage){
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);
        Integer offset = page < 1 ? 0 : size * (page - 1);
        List<Question> questionList = questionDao.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        if (questionList != null && questionList.size() > 0) {
            for (Question question : questionList) {
                QuestionDTO questionDTO = new QuestionDTO();
                User user = userDao.findUserById(question.getCreator());
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
        }
        paginationDTO.setData(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = questionDao.countByCreator(userId);
        if (totalCount % size == 0){
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1){
            page = 1;
        }
        if (page > totalPage){
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);
        Integer offset = page < 1 ? 0 : size * (page - 1);
        List<Question> questionList = questionDao.listByCreator(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        if (questionList != null && questionList.size() > 0) {
            for (Question question : questionList) {
                QuestionDTO questionDTO = new QuestionDTO();
                User user = userDao.findUserById(question.getCreator());
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
        }
        paginationDTO.setData(questionDTOList);
        return paginationDTO;
    }
}
