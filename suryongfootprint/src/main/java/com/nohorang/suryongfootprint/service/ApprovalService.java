package com.nohorang.suryongfootprint.service;

import com.nohorang.suryongfootprint.model.Approval;
import com.nohorang.suryongfootprint.model.Count;
import com.nohorang.suryongfootprint.model.Report;
import com.nohorang.suryongfootprint.model.User;
import com.nohorang.suryongfootprint.model.request.ApprovalReportRequest;
import com.nohorang.suryongfootprint.repository.ApprovalRepository;
import com.nohorang.suryongfootprint.repository.CountRepository;
import com.nohorang.suryongfootprint.repository.ReportRepository;
import com.nohorang.suryongfootprint.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApprovalService {
    private final ApprovalRepository approvalRepository;
    private final ReportRepository reportRepository;

    private final CountRepository countRepository;
    private final UserRepository userRepository;

    //count에 대한 user의 승인 여부 가져오기
    public Optional<Approval> getUserApproval(int count_id, String user_id){
        Optional<Count> countForId =  countRepository.findById(count_id);
        if (!countForId.isPresent()) {
            throw new EntityNotFoundException("Count not present in the database");
        }
        Optional<User> userForId = userRepository.findById(user_id);
        if (!userForId.isPresent()) {
            throw new EntityNotFoundException("User not present in the database");
        }
        Count count = countForId.get();
        User user = userForId.get();
        Optional<Approval> userApproval = approvalRepository.findByCountAndUser(count, user);

        return userApproval;
    }

    //count에 대한 user의 신고 여부 가져오기
    public Optional<Report> getUserReport(int count_id, String user_id){
        Optional<Count> countForId = countRepository.findById(count_id);
        if (!countForId.isPresent()) {
            throw new EntityNotFoundException("Count not present in the database");
        }
        Optional<User> userForId = userRepository.findById(user_id);
        if (!userForId.isPresent()) {
            throw new EntityNotFoundException("User not present in the database");
        }
        Count count = countForId.get();
        User user = userForId.get();
        Optional<Report> userReport = reportRepository.findByCountAndUser(count, user);

        return userReport;
    }

    //승인하기
    public String UserApproveCount(ApprovalReportRequest request){
        Optional<Count> countForId = countRepository.findById(request.getCountId());
        if (!countForId.isPresent()) {
            throw new EntityNotFoundException("Count not present in the database");
        }

        Optional<User> userForId = userRepository.findById(request.getUserId());
        if (!userForId.isPresent()) {
            throw new EntityNotFoundException("User not present in the database");
        }

        Count count = countForId.get();
        User user = userForId.get();

        Approval approval= new Approval();
        approval.setUser(user);
        approval.setCount(count);
        approvalRepository.save(approval);

        //count의 승인 횟수 +1
        count.setApprovalCount(count.getApprovalCount()+1);

        //승인 횟수 체크
        if (count.getApprovalCount()>=5){
            count.setState(2);
            countRepository.save(count);
            return "full approval";
        }else {
            countRepository.save(count);
            return "approved";
        }
    }

    //신고하기
    public String UserReportCount(ApprovalReportRequest request){
        Optional<Count> countForId = countRepository.findById(request.getCountId());
        if (!countForId.isPresent()) {
            throw new EntityNotFoundException("Count not present in the database");
        }

        Optional<User> userForId = userRepository.findById(request.getUserId());
        if (!userForId.isPresent()) {
            throw new EntityNotFoundException("User not present in the database");
        }

        Count count = countForId.get();
        User user = userForId.get();

        Report report= new Report();
        report.setUser(user);
        report.setCount(count);
        reportRepository.save(report);

        //count의 승인 횟수 -1
        count.setApprovalCount(count.getApprovalCount()-1);

        //승인 횟수 체크
        if (count.getApprovalCount()<=0){
            return "full report";
        }else {
            return "reported";
        }
    }
}
