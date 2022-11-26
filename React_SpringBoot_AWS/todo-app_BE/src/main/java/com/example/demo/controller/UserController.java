package com.example.demo.controller;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.UserEntity;
import com.example.demo.security.TokenProvider;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenProvider;

    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {
            if (userDTO == null || userDTO.getPassword() == null) {
                throw new RuntimeException("Invalid Password value.");
            }
            // 요청을 이용해 유저를 저장합니다.
            UserEntity user = UserEntity.builder()
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword())
                    .build();
            // 서비스를 이용해 리포지터리에 유저를 저장합니다.
            UserEntity registeredUser = userService.create(user);
            UserDTO responseUserDTO = UserDTO.builder()
                    .id(registeredUser.getId())
                    .username(registeredUser.getUsername())
                    .build();

            return ResponseEntity.ok().body(responseUserDTO);
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity
                    .badRequest()
                    .body(responseDTO);
        }
    }

    // 로그인(Jwt 적용)
    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
        UserEntity user = userService.getByCredentials(
                userDTO.getUsername(),
                userDTO.getPassword());

                if (user != null) {
                    final String token = tokenProvider.create(user);
                    final UserDTO responseUserDTO = UserDTO.builder()
                            .username(user.getUsername())
                            .id(user.getId())
                            .token(token)
                            .build();
                    return ResponseEntity.ok().body(responseUserDTO);
                } else {
                    ResponseDTO responseDTO = ResponseDTO.builder()
                            .error("Login failed.")
                            .build();
                    return ResponseEntity
                            .badRequest()
                            .body(responseDTO);
                }

    }
}
