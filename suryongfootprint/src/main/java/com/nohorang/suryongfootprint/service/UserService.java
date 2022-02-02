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


}
