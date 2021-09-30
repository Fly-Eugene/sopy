package com.ssafy.sopy.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.sopy.dto.UserDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String username;

    @Column
    private Integer age;

    @Column(length = 30)
    private String department;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private UserImage userImage;

    @OneToMany(mappedBy = "user")
    private List<UserLike> userLikeList = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;


    public User() {
    }

    @Builder
    public User(Long id, String email, String password, String username, Integer age, String department, UserImage userImage) {
        this(id, email, password, username, age, department, userImage, Collections.singleton(Authority.builder().authorityName("ROLE_USER").build()));
    }

    @Builder
    public User(Long id, String email, String password, String username, Integer age, String department, UserImage userImage, Set<Authority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.age = age;
        this.department = department;
        this.userImage = userImage;
        this.authorities = authorities;
    }


    public UserDto entityToDto(){
        return UserDto.builder().id(id).email(email).username(username).age(age).department(department).userImage(userImage.entityToDto()).build();
    }
}
