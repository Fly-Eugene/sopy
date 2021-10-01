package com.ssafy.sopy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserReqDto {
    private MultipartFile userImage;
    private Long id;
    private String email;
    private String password;
    private String username;
    private Integer age;
    private String department;

    public UserReqDto() {
    }

    @Builder
    public UserReqDto(MultipartFile userImage, Long id, String email, String password, String username, Integer age, String department) {
        this.userImage = userImage;
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.age = age;
        this.department = department;
    }

    @Override
    public String toString() {
        return "UserReqDto{" +
                "userImage=" + userImage +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                '}';
    }
}
