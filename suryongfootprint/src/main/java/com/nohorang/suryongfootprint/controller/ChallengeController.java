package com.nohorang.suryongfootprint.controller;

import com.nohorang.suryongfootprint.model.Challenge;
import com.nohorang.suryongfootprint.model.Count;
import com.nohorang.suryongfootprint.model.Post;
import com.nohorang.suryongfootprint.model.User;
import com.nohorang.suryongfootprint.model.request.PostCreationRequest;
import com.nohorang.suryongfootprint.model.request.UserCreationRequest;
import com.nohorang.suryongfootprint.model.response.PostCreationResponse;
import com.nohorang.suryongfootprint.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/s-footprint/challenge")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ChallengeController {
    private final ChallengeService challengeService;

    //전체 챌린지 가져오기
    @GetMapping("")
    public ResponseEntity<List<Challenge>> readChallenges () {
        return ResponseEntity.ok(challengeService.readChallenges());
    }

    //챌린지 아이디로 가져오기
    @GetMapping("/{challenge_id}")
    public ResponseEntity<Challenge> getChallenge (@PathVariable int challenge_id) {
        return ResponseEntity.ok(challengeService.getChallenge(challenge_id));
    }

    //챌린지 참여 - 포스트 올림
    @PostMapping("/post")
    public ResponseEntity<PostCreationResponse> createPost(@RequestBody PostCreationRequest postCreationRequest){
        return ResponseEntity.ok(challengeService.createPost(postCreationRequest));
    }

    //user 포스트 가져오기
    @GetMapping("/post/{user_id}")
    public ResponseEntity<List<Post>> getUserPosts (@PathVariable String user_id) {
        return ResponseEntity.ok(challengeService.getUserPosts(user_id));
    }

    // 전체 COUNT 가져오기
    @GetMapping("/count")
    public ResponseEntity<List<Count>> readCounts () {
        return ResponseEntity.ok(challengeService.readCounts());
    }

}
