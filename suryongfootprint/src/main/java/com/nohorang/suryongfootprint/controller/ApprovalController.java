package com.nohorang.suryongfootprint.controller;

import com.nohorang.suryongfootprint.model.*;
import com.nohorang.suryongfootprint.model.request.ApprovalReportRequest;
import com.nohorang.suryongfootprint.model.request.PostCreationRequest;
import com.nohorang.suryongfootprint.model.request.UserCreationRequest;
import com.nohorang.suryongfootprint.service.ApprovalService;
import com.nohorang.suryongfootprint.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/s-footprint")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ApprovalController {
    private final ApprovalService approvalService;

    //COUNT에 대한 USER의 승인 여부 가져오기 (버튼 비활성화용)
    @GetMapping("/approval/count/{count_id}/user/{user_id}")
    public ResponseEntity<Optional<Approval>> getUserApproval (@PathVariable("count_id") int count_id, @PathVariable("user_id") String user_id) {
        return ResponseEntity.ok(approvalService.getUserApproval(count_id, user_id));
    }

    //COUNT에 대한 USER의 신고 여부 가져오기(버튼 비활성화용)
    @GetMapping("/report/count/{count_id}/user/{user_id}")
    public ResponseEntity<Optional<Report>> getUserReport (@PathVariable("count_id") int count_id, @PathVariable("user_id") String user_id) {
        return ResponseEntity.ok(approvalService.getUserReport(count_id, user_id));
    }

    //승인하기
    @PostMapping("/approval")
    public ResponseEntity<String> UserApproveCount(@RequestBody ApprovalReportRequest approvalReportRequest){
        return ResponseEntity.ok(approvalService.UserApproveCount(approvalReportRequest));
    }

    //신고하기
    @GetMapping("/report")
    public ResponseEntity<String> UserReportCount(@RequestBody ApprovalReportRequest approvalReportRequest) {
        return ResponseEntity.ok(approvalService.UserReportCount(approvalReportRequest));
    }


}
