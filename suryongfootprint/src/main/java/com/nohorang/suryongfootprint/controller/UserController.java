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

    //아이디 찾기
    @GetMapping("/user/user_id")
    public ResponseEntity<String> findUserId(@RequestParam String user_name,@RequestParam String user_email){
        return ResponseEntity.ok(userService.findUserId(user_name,user_email));
    }
//    //비밀번호 찾기
    @GetMapping("/user/user_pw")
    public ResponseEntity<String> findUserPW(@RequestParam String user_name, @RequestParam String user_id, @RequestParam String user_email){
        return ResponseEntity.ok(userService.findUserPW(user_name, user_id, user_email));
    }

    //비밀번호 수정
    //UserCrearionRequest에서 user_pw값만 사용 -> 나머지는 빈 값이어도 됨.
    @PatchMapping("/user/{user_id}/pw")
    public ResponseEntity<User> updateUserPW(@PathVariable("user_id") String user_id, @RequestBody UserCreationRequest request){
        return ResponseEntity.ok(userService.updateUserPW(user_id, request));
    }

    //닉네임 수정
    //UserCrearionRequest에서 user_nickname값만 사용 -> 나머지는 빈 값이어도 됨.
    @PatchMapping("/user/{user_id}/nickname")
    public ResponseEntity<User> updateUserNickname(@PathVariable("user_id") String user_id, @RequestBody UserCreationRequest request){
        return ResponseEntity.ok(userService.updateUserNickName(user_id, request));
    }

    //회원 탈퇴
    @DeleteMapping("/user/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String user_id){
        userService.deleteUser(user_id);
        return ResponseEntity.ok().build();
    }


}
