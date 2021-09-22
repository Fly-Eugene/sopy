package com.ssafy.sopy.service;

import com.ssafy.sopy.domain.entity.User;
import com.ssafy.sopy.domain.entity.UserImage;
import com.ssafy.sopy.domain.repository.UserRepository;
import com.ssafy.sopy.dto.UserDto;
import com.ssafy.sopy.dto.UserReqDto;
import com.ssafy.sopy.jwt.JwtFilter;
import com.ssafy.sopy.jwt.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final ImageService imageService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, ImageService imageService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.imageService = imageService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public Object login(UserDto user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        Map<String, Object> map = new HashMap<>();
        map.put("token", jwt);
        map.put("user", userRepository.findByEmail(user.getEmail()).entityToDto());
        return map;
    }

    public Object join(UserReqDto param) throws IOException {
        UserImage userImage = imageService.makeUserImage(param.getUserImage());
        return userRepository.save(User.builder().email(param.getEmail()).password(passwordEncoder.encode(param.getPassword()))
                .username(param.getUsername()).age(param.getAge()).department(param.getDepartment())
                .userImage(userImage).build()).entityToDto();
    }
}
