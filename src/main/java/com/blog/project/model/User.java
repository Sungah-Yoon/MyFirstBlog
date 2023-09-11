package com.blog.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// ORM -> Java(다른언어) Object -> 테이블로 매핑해주는 기술
@Entity    // User 클래스가 MySQL에 테이블이 생성된다
public class User {

    @Id    // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;               // Auto_increment

    @Column(nullable = false, length = 30)
    private String username;      // 아이디

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'")
    private String role;

    @CreationTimestamp           // 시간이 자동으로 입력됨
    private Timestamp createDate;
}
