package com.blog.project.repository;

import com.blog.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// DAO
// 자동으로 bean으로 등록됨
// @Repository -> 생략 가능함
public interface UserRepository extends JpaRepository<User, Integer> {  // JpaRepository가 관리하는 table은 User, 이 table의 primary key는 Integer

}
