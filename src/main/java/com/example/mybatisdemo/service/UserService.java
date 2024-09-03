package com.example.mybatisdemo.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mybatisdemo.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    private final SqlSession sqlSession;

    public UserService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public User findById(Long id) {
        return sqlSession.selectOne("UserMapper.findById", id);
    }

    public List<User> findAll() {
        return sqlSession.selectList("UserMapper.findAll");
    }

    @Transactional
    public void save(User user) {
    	log.info("save call.");
        if (user.getId() == null) {
            sqlSession.insert("UserMapper.insert", user);//
            sqlSession.insert("UserMapper.insert2", user);
        } else {
            sqlSession.update("UserMapper.update", user);
            sqlSession.insert("UserMapper.insert2", user);
        }
    }

    public void delete(Long id) {
        sqlSession.delete("UserMapper.delete", id);
    }
}
