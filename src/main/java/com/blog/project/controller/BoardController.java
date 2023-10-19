package com.blog.project.controller;

import com.blog.project.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 컨트롤러에서 세션 찾는 방법
    // @AuthenticationPrincipal PrincipalDetail principal <- 파라미터로 받아서 사용

    @GetMapping({"","/"})
    public String index(Model model) {
        model.addAttribute("boards", boardService.글목록());
        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }
}
