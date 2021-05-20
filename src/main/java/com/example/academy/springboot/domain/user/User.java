package com.example.academy.springboot.domain.user;

import com.example.academy.springboot.domain.posts.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String pictures;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String pictures, Role role){
        this.name = name;
        this.email = email;
        this.pictures = pictures;
        this.role = role;
    }

    public User update(String name, String pictures){
        this.name = name;
        this.pictures = pictures;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
