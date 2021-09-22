package com.ssafy.sopy.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.sopy.dto.UserDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
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

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    public User() {
    }

    @Builder
    public User(Long id, String email, String password, String username, Integer age, String department, Set<Authority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.age = age;
        this.department = department;
        this.authorities = authorities;
    }

    public UserDto entityToDto(){
        return UserDto.builder().id(id).email(email).password(password).username(username).age(age).department(department).build();
    }
}
