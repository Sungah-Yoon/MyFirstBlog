package com.blog.project.repository;

import com.blog.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// DAO
// 자동으로 bean으로 등록됨
// @Repository -> 생략 가능함
public interface UserRepository extends JpaRepository<User, Integer> {

    // SELECT * FROM user WHERE username = ?;
    Optional<User> findByUsername(String username);

}



//JPA Naming 전략
// SELECT * FROM user WHERE username=? AND password=?;
// User findByUsernameAndPassword(String username, String password);

//    @Query(value = "SELECT * FROM user WHERE username=?1 AND password=?2", nativeQuery = true)
//    User login(String username, String password);
