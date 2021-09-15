package com.ssafy.sopy.controller;

import com.ssafy.sopy.domain.entity.User;
import com.ssafy.sopy.domain.repository.UserRepository;
import com.ssafy.sopy.dto.UserDto;
import com.ssafy.sopy.jwt.JwtFilter;
import com.ssafy.sopy.jwt.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;

    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public Object authorize(@Valid @RequestBody UserDto user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        // 여기서 CustomUserDetailsService - loadUserByUserName 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        Map<String, Object> map = new HashMap<>();
        map.put("token", jwt);
        User entity = userRepository.findByEmail(user.getEmail()).get();
//        Profile profile = entity.getProfile();
//        MemberResDto dto = MemberResDto.builder().id(entity.getId())
//                .email(entity.getEmail())
//                .password(entity.getPassword())
//                .username(entity.getUsername())
//                .age(entity.getAge())
//                .department(entity.getDepartment())
//                .profile(profile == null ? null : ProfileDto.builder().id(profile.getId()).image(profile.getImage()).path(profile.getPath()).thumbnail(profile.getThumbnail()).imageOrgName(profile.getImageOrgName()).build())
//                .build();
//
//        map.put("member", dto);
//        return ApiResult.builder().status(StatusCode.OK)
//                .message(ResponseMessage.LOGIN_SUCCESS)
//                .dataType("member, token").data(map).build();
        return null;
    }
}
