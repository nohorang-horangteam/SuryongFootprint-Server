package com.nohorang.suryongfootprint.service;

import com.nohorang.suryongfootprint.model.Challenge;
import com.nohorang.suryongfootprint.model.Count;
import com.nohorang.suryongfootprint.model.Post;
import com.nohorang.suryongfootprint.model.User;
import com.nohorang.suryongfootprint.model.request.PostCreationRequest;
import com.nohorang.suryongfootprint.model.response.PostCreationResponse;
import com.nohorang.suryongfootprint.repository.ChallengeRepository;
import com.nohorang.suryongfootprint.repository.CountRepository;
import com.nohorang.suryongfootprint.repository.PostRepository;
import com.nohorang.suryongfootprint.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final UserRepository userRepository;
    private final ChallengeRepository challengeRepository;
    private final PostRepository postRepository;
    private final CountRepository countRepository;

    //전체 챌린지 읽어오기
    public List<Challenge> readChallenges() {
        return challengeRepository.findAll();
    }

    //전체 참여횟수 읽어오기
    public List<Count> readCounts() {
        return countRepository.findAll();
    }

    //전체 챌린지 읽어오기 -> 참여자 수 내림차순으로 가져올 수 있는 방법?

    //챌린지 id로 읽어오기
    public Challenge getChallenge(int user_id){
        Optional<Challenge> challenge= challengeRepository.findById(user_id);
        if(challenge.isPresent()){
            return challenge.get();
        }
        throw new EntityNotFoundException("Cant find any challenge under given ID");
    }

    //참여 생성
    public PostCreationResponse createPost(PostCreationRequest request) {
        Optional<User> userForId = userRepository.findById(request.getUserId());
        if (!userForId.isPresent()) {
            throw new EntityNotFoundException("User not present in the database");
        }
        User user = userForId.get();

        Optional<Challenge> challengeForId = challengeRepository.findById(request.getChallengeId());
        if (!challengeForId.isPresent()) {
            throw new EntityNotFoundException("Challenge not present in the database");
        }
        Challenge challenge = challengeForId.get();

        //Count 참여 횟수 테이블 - > state가 0인 count가 존재하는지 찾기
        List<Count> countForId = countRepository.findByUserAndChallenge(user,challenge);
        Count new_count = new Count();
        boolean flag_count = false;
        if (!countForId.isEmpty()) {//count가 있을 때
            for (int i=0; i< countForId.size();i++) {
                if (countForId.get(i).getState() == 0) {//state가 0인 count가 있을 때
                    BeanUtils.copyProperties(countForId.get(i), new_count);
                    int p_cnt = new_count.getPostCount();
                    new_count.setPostCount(p_cnt);
                    if (p_cnt == challenge.getCondition()) new_count.setState(1);//챌린지 참여개수가 조건을 충족하면 count의 state는 0 -> 1
                    countRepository.save(new_count);
                    flag_count=true;
                }
            }
        }
        //state가 0인 count가 없을 때
        if(flag_count==false){
            new_count.setUser(user);
            new_count.setChallenge(challenge);
            new_count.setPostCount(1);
            new_count.setApprovalCount(2);
            new_count.setState(0);
            countRepository.save(new_count);
        }

        Post new_post = new Post();
        new_post.setUser(user);
        new_post.setChallenge(challenge);
        new_post.setCount(new_count);
        new_post.setContent(request.getContent());
        new_post.setDate(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        new_post.setImg(Base64.getDecoder().decode(request.getImg()));//base64 string으로 image를 받고, 디코딩하여 db에 저장

        new_post=postRepository.save(new_post);

        PostCreationResponse pr = new PostCreationResponse();
        pr.setPostId(new_post.getPostId());
        pr.setUserId(user.getUserId());
        pr.setChallengeId(challenge.getChallengeId());
        pr.setCountId(new_count.getCountId());
        pr.setContent(new_post.getContent());
        pr.setImg(request.getImg());
        pr.setDate(new_post.getDate());

        return pr;
    }

    //사용자의 참여(Post) 조회
    public List<Post> getUserPosts(String user_id){
        Optional<User> userForId = userRepository.findById(user_id);
        if (!userForId.isPresent()) {
            throw new EntityNotFoundException("User not present in the database");
        }
        User user = userForId.get();

        List<Post> user_posts= postRepository.findByUser(user);
        if(user_posts.isEmpty()){
            throw new EntityNotFoundException("Cant find any post under given ID");
        }
        return user_posts;
    }
}
