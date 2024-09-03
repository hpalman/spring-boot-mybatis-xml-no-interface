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
    public void save(User user) throws Exception {
    	log.info("save call.");
        if (user.getId() == null) {
        	log.info("UserMapper.insert I");
            sqlSession.insert("UserMapper.insert", user);//
        	log.info("UserMapper.insert O");
        	
        	//log.info("UserMapper.insert2 I");
            //user.setId(1L);
            //sqlSession.insert("UserMapper.insert2", user);
        	//log.info("UserMapper.insert2 O");

        } else {
        	log.info("UserMapper.update I");
            sqlSession.update("UserMapper.update", user);
        	log.info("UserMapper.update O");

        	//log.info("UserMapper.insert2 I");
        	//user.setId(2L);
            //sqlSession.insert("UserMapper.insert2", user);
        	//log.info("UserMapper.insert2 O");
        }
        throw new Exception("rollback test");
    }

    public void delete(Long id) {
        sqlSession.delete("UserMapper.delete", id);
    }
}
