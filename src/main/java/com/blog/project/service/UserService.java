package com.blog.project.service;

import com.blog.project.model.User;
import com.blog.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌 -> IoC를 해줌
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)    // select 시, 트랜잭션 시작. 서비스 종료 시에 트랜잭션 종료(정합성)
    public User 로그인(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
