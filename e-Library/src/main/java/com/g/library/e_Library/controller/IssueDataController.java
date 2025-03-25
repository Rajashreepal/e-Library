package com.g.library.e_Library.controller;

import com.g.library.e_Library.dto.IssueDataDto;
import com.g.library.e_Library.entity.IssueData;
 import com.g.library.e_Library.service.IssueDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/issue_data")
public class IssueDataController {

    private final IssueDataService issueDataService;

    @Autowired
    public IssueDataController(IssueDataService issueDataService) {
        this.issueDataService = issueDataService;
    }

    @PostMapping
    public ResponseEntity<IssueData> addIssueData(@RequestBody IssueDataDto issueDataDto) {
        IssueData issueData = this.issueDataService.addIssueData ( issueDataDto );
        return new ResponseEntity<> ( issueData, HttpStatus.OK );
    }

    @GetMapping
    public ResponseEntity<List<IssueData>> getIssueDataByMemberId(@RequestParam UUID memberId) {
        List<IssueData> issueDataList = this.issueDataService.getIssueDataByMemberId ( memberId );
        return new ResponseEntity<> ( issueDataList, HttpStatus.OK );
    }
}