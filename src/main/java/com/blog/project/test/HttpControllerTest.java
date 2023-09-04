package com.blog.project.test;

import org.springframework.web.bind.annotation.*;

// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest : ";

//    // 1. 생성자 어노테이션 사용해 lombokTest
//    @GetMapping("/blog/http/lombok")
//    public String lombokTest() {
//        Member m = new Member(1, "ssar", "1234", "email");
//        System.out.println(TAG+"getter : " + m.getId());
//        m.setId(5000);
//        System.out.println(TAG + "setter : " + m.getId());
//        return "lombok test 완료";
//    }

    // 2. Builder 어노테이션 사용해 lombokTest
    // http://localhost:8085/blog/http/lombok2
    @GetMapping("/http/lombok2")
    public String lombokTest2() {
        Member m = Member.builder()
                .username("ssar")
                .password("1234")
                .email("ssar@nate.com")
                .build();
        System.out.println(TAG+"getter : " + m.getId());
        m.setId(5000);
        System.out.println(TAG + "setter : " + m.getId());
        return "lombok test 완료";
    }


    // 인터넷 브라우저 요청은 무조건 get 요청 밖에 할 수 없다.
    // get 요청 시, ? 뒤 query string 을 통해 데이터를 전송할 수 있다.
    // http://localhost:8085/http/get (select)
    @GetMapping("/http/get")
    public String getTest(@RequestParam int id, @RequestParam String username){
        return "get 요청 " + id + ", " + username;
    }

    @GetMapping("/http/get2")
    public String getTest2(Member m){
        return "get 요청 " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // http://localhost:8085/http/post (insert)
    @PostMapping("/http/post")
    public String postTest(Member m){
        return "post 요청 " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // http://localhost:8085/http/post2 (insert)
    @PostMapping("/http/post2")  // text/plain
    public String postTest2(@RequestBody String text){
        return "post 요청 " + text;
    }

    // http://localhost:8085/http/post3 (insert)
    @PostMapping("/http/post3")  // application/json
    public String postTest3(@RequestBody Member m) {   // MessageConverter (스프링 부트)
        return "post 요청 " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // http://localhost:8085/http/put (update)
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m){
        return "put 요청" + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // http://localhost:8085/http/delete (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }

}
