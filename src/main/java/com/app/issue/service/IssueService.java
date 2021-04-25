package com.app.issue.service;

import com.app.issue.dto.CreateIssueRequest;
import com.app.issue.dto.IssueResponse;
import com.app.issue.entity.Issue;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    Issue create(CreateIssueRequest createIssueRequest);

    List<IssueResponse> findAll();

    Optional<IssueResponse> findById(Long id);
}
