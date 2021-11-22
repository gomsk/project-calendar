package com.gomsk.project.core.service;


import com.gomsk.project.core.domain.entity.User;
import com.gomsk.project.core.domain.entity.repository.UserRepository;
import com.gomsk.project.core.dto.UserCreateReq;
import com.gomsk.project.core.util.Encrypter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Encrypter encrypter;
    private final UserRepository userRepository;

    @Transactional
    public User create(UserCreateReq userCreateReq) {
        userRepository.findByEmail(userCreateReq.getEmail())
                .ifPresent(x -> {
                   throw new RuntimeException("user already existed!");
                });

        return userRepository.save(new User(
                userCreateReq.getName(),
                userCreateReq.getEmail(),
                encrypter.encrypt(userCreateReq.getPassword()),
                userCreateReq.getBirthday()
        ));
    }
    @Transactional
    public Optional<User> findPwMatchUser(String email, String password) {
        return userRepository.findByEmail(email)
                .map(user -> user.isMatch(encrypter,password) ? user : null);
    }
}
