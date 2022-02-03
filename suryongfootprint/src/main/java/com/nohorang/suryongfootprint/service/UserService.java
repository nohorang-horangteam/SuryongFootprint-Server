package com.nohorang.suryongfootprint.service;

import com.nohorang.suryongfootprint.model.User;
import com.nohorang.suryongfootprint.model.request.UserCreationRequest;
import com.nohorang.suryongfootprint.model.request.UserLoginRequest;
import com.nohorang.suryongfootprint.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //회원가입
    public User createUser(UserCreationRequest request){
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return userRepository.save(user);
    }

    //로그인
    public User getUserLogin(UserLoginRequest loginRequest){
        Optional<User> user = userRepository.findById(loginRequest.getUser_id());
        if(user.isPresent()){
            if(user.get().getUser_pw().equals(loginRequest.getUser_pw())) {
                return user.get();
            }
        }
        //로그인 실패
        throw new EntityNotFoundException("Cant find any user under given ID and PW");
    }

    //아이디 찾기
    public String findUserId(String user_name, String user_email){
        Optional<User> user = userRepository.findByUser_nameAndUser_email(user_name,user_email);
        if(user.isPresent()){
            return user.get().getUser_id();
        }
        //아이디 찾기 실패
        throw new EntityNotFoundException("Cant find any user given Info");
    }

    //비밀번호 찾기
    public String findUserPW(String user_id, String user_name, String user_email){
        Optional<User> user = userRepository.findByUser_idAndUser_nameAndUser_email(user_id,user_name,user_email);
        if(user.isPresent()){
            return user.get().getUser_pw();
        }
        //아이디 찾기 실패
        throw new EntityNotFoundException("Cant find any user given Info");
    }

    //비밀번호 변경
    public User updateUserPW(String user_id, UserCreationRequest request){
        Optional<User> user = userRepository.findById(request.getUser_id());
        if (!user.isPresent()) {
            throw new EntityNotFoundException("User Not Found");
        }
        User c_user = user.get();
        c_user.setUser_pw(request.getUser_pw());
        return userRepository.save(c_user);
    }

    //닉네임 변경
    public User updateUserNickName(String user_id, UserCreationRequest request){
        Optional<User> user = userRepository.findById(request.getUser_id());
        if (!user.isPresent()) {
            throw new EntityNotFoundException("User Not Found");
        }
        User c_user = user.get();
        c_user.setUser_nickname(request.getUser_nickname());
        return userRepository.save(c_user);
    }
}
