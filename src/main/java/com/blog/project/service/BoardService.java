package com.blog.project.service;

import com.blog.project.model.Board;
import com.blog.project.model.RoleType;
import com.blog.project.model.User;
import com.blog.project.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void 글쓰기(Board board, User user) {     // title, content
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

}