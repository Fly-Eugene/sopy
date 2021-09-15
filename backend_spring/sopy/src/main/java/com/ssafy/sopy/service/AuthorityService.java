package com.ssafy.sopy.service;

import com.ssafy.sopy.domain.entity.User;
import com.ssafy.sopy.domain.repository.UserRepository;
import com.ssafy.sopy.util.SecurityUtil;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AuthorityService {
    private final UserRepository userRepository;

    public AuthorityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Optional<User> getMemberWithAuthorities(String email){
        return userRepository.findOneWithAuthoritiesByEmail(email);
    }

    @Transactional
    public Optional<User> getMyMemberWithAuthorities(){
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByEmail);
    }
}
