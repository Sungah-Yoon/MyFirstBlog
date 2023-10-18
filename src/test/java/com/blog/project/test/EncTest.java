package com.blog.project.test;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class EncTest {

    @Test
    public void 해쉬_암호화() {
        String encPassword = new BCryptPasswordEncoder().encode("!234");
        System.out.println("1234 해쉬화 : " + encPassword);
    }
}