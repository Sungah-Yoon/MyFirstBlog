package com.blog.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    // 컨트롤러에서 세션 찾는 방법
    // @AuthenticationPrincipal PrincipalDetail principal <- 파라미터로 받아서 사용

    @GetMapping({"","/"})
    public String index() {
        // /WEB-INF/views/index.jsp
        return "index";
    }

    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }
}
