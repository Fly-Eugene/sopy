package com.ssafy.sopy.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String username;
    private Integer age;
    private String department;
    private ImageDto userImage;

    public UserDto() {
    }

    @Builder
    public UserDto(Long id, String email, String password, String username, Integer age, String department, ImageDto userImage) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.age = age;
        this.department = department;
        this.userImage = userImage;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                ", userImage=" + userImage +
                '}';
    }
}
