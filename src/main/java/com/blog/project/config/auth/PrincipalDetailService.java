package com.blog.project.config.auth;

import com.blog.project.model.User;
import com.blog.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Bean 등록
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // userRepo 에서 반환하는 객체를 Optional 타입으로 설정하였으므로, orElseThrow 붙여줘야 함
        User principal = userRepository.findByUsername(username)
                .orElseThrow(()-> {
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.: " + username);
                });

        return new PrincipalDetail(principal);   // 시큐리티의 세션에 유저 정보가 저장됨
    }
}
