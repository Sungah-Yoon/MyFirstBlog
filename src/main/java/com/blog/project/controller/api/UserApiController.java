package com.blog.project.controller.api;

import com.blog.project.dto.ResponseDto;
import com.blog.project.model.RoleType;
import com.blog.project.model.User;
import com.blog.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController: save 호출됨");
        userService.회원가입(user);                    // 1이면 성공, -1이면 실패

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);   // 자바 오브젝트를 JSON으로 변환해, 리턴(Jackson)
    }

    // 전통적인 방식의 로그인
/*인   @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
        System.out.println("UserApiController: login 호출됨");
        User principal = userService.로그인(user);                         // principal (접근주체)

        if (principal != null) {
            session.setAttribute("principal", principal);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }*/


}
