package com.nohorang.suryongfootprint.controller;

import com.nohorang.suryongfootprint.model.User;
import com.nohorang.suryongfootprint.model.request.UserCreationRequest;
import com.nohorang.suryongfootprint.model.request.UserLoginRequest;
import com.nohorang.suryongfootprint.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/s-footprint")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //회원가입
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody UserCreationRequest request){
        return ResponseEntity.ok(userService.createUser(request));
    }

    //로그인
    @PostMapping("/user/login")
    public ResponseEntity<User> getUserLogin(@RequestBody UserLoginRequest request){
        return ResponseEntity.ok(userService.getUserLogin(request));
    }
}
