package com.lbt.community_stu.service;

import com.lbt.community_stu.dao.QuestionMapper;
import com.lbt.community_stu.dao.UserMapper;
import com.lbt.community_stu.dto.PaginationDTO;
import com.lbt.community_stu.dto.QuestionDTO;
import com.lbt.community_stu.model.Question;
import com.lbt.community_stu.model.QuestionExample;
import com.lbt.community_stu.model.User;
import org.apache.ibatis.session.RowBounds;
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
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());
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
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        if (questionList != null && questionList.size() > 0) {
            for (Question question : questionList) {
                QuestionDTO questionDTO = new QuestionDTO();
                User user = userMapper.selectByPrimaryKey(question.getCreator());
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
        //Integer totalCount = questionMapper.countByCreator(userId);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int)questionMapper.countByExample(example);
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
        //List<Question> questionList = questionDao.listByCreator(userId,offset,size);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(questionExample,new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        if (questionList != null && questionList.size() > 0) {
            for (Question question : questionList) {
                QuestionDTO questionDTO = new QuestionDTO();
                User user = userMapper.selectByPrimaryKey(question.getCreator());
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
        }
        paginationDTO.setData(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getQuestionById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null){
            //插入
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else {
            //更新
            Question upQuestion = new Question();
            upQuestion.setTitle(question.getTitle());
            upQuestion.setDescription(question.getDescription());
            upQuestion.setTag(question.getTag());
            upQuestion.setGmtModified(System.currentTimeMillis());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            questionMapper.updateByExampleSelective(upQuestion,example);
            //questionDao.update(question);
        }
    }
}
