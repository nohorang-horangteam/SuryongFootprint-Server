package com.nohorang.suryongfootprint.service;

import com.nohorang.suryongfootprint.model.Approval;
import com.nohorang.suryongfootprint.model.Count;
import com.nohorang.suryongfootprint.model.Report;
import com.nohorang.suryongfootprint.repository.ApprovalRepository;
import com.nohorang.suryongfootprint.repository.CountRepository;
import com.nohorang.suryongfootprint.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApprovalService {
    private final ApprovalRepository approvalRepository;
    private final ReportRepository reportRepository;

    private final CountRepository countRepository;

    //count의 승인 목록 가져오기
    public List<Approval> getApproval(int count_id){
        Optional<Count> countForId =  countRepository.findById(count_id);
        if (!countForId.isPresent()) {
            throw new EntityNotFoundException("Count not present in the database");
        }
        Count count = countForId.get();
        List<Approval> approvalList = approvalRepository.findByCount(count);

        return approvalList;
    }

    //count의 신고 목록 가져오기
    public List<Report> getReport(int count_id){
        Optional<Count> countForId = countRepository.findById(count_id);
        if (!countForId.isPresent()) {
            throw new EntityNotFoundException("Count not present in the database");
        }
        Count count = countForId.get();
        List<Report> reportList = reportRepository.findByCount(count);

        return reportList;
    }

    //승인하기

    //신고하기

}
