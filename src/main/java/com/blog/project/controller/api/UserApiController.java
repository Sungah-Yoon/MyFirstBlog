package com.blog.project.controller.api;

import com.blog.project.dto.ResponseDto;
import com.blog.project.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController: save 호출됨");
        return new ResponseDto<Integer>(HttpStatus.OK, 1);   // status 값만 보고 정상적으로 수행됐는지를 판단 가능함. 여기서 data는 크게 의미 없음
    }
}
