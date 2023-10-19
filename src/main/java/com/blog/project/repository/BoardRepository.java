package com.blog.project.repository;

import com.blog.project.model.Board;
import com.blog.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
