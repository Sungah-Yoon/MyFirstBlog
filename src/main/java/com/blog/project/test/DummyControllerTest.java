package com.blog.project.test;

import com.blog.project.model.RoleType;
import com.blog.project.model.User;
import com.blog.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Transactional
@RestController
public class DummyControllerTest {

    @Autowired   // 의존성 주입(DI)
    private UserRepository userRepository;


    // http://localhost:8085/blog/dummy/user/1
    @DeleteMapping("dummy/user/{id}")
    public String delete(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            return "삭제에 실패하였습니다. 해당 id 는 DB에 없습니다.";
        }

        userRepository.deleteById(id);
        return "삭제되었습니다. id: " + id;

    }


    // http://localhost:8085/blog/dummy/user/1
    // email, password Json 데이터로 입력받기 -> @RequestBody
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {

        System.out.println("id : " + id);
        System.out.println("password : " + requestUser.getPassword());
        System.out.println("email : " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        // userRepository.save(user);

        // 더티 체킹을 이용한 update
        return user;
    }



    // http://localhost:8085/blog/dummy/users
    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    // http://localhost:8085/blog/dummy/user
    // 페이징 기본 전략 : 한 페이지 당 2건의 데이터를 받아오고, sort 는 id 로 하고, id 는 최근에 가입한 순서(DESC)로 정렬함
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }


    // {id} 주소로 파라미터를 전달 받을 수 있음
    // http://localhost:8085/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){

//        // 람다식
//        User user = userRepository.findById(id).orElseThrow(()->{
//            return new IllegalArgumentException("해당 사용자는 없습니다.");
//        });

        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
            }
        });
        return user;
    }


    // http://localhost:8085/blog/dummy/join (요청)
    // http 의 body 에 username, password, email 데이터를 가지고 (요청)
    @PostMapping("/dummy/join")
    public String join(User user) {  // key=value (약속된 규칙)
        System.out.println("id : " + user.getId());                   // null(=0)
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());
        System.out.println("role : " + user.getRole());               // null
        System.out.println("createDate : " + user.getCreateDate());   // null

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다";
    }
}
