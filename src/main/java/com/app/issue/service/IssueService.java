package com.app.issue.service;

import com.app.issue.dto.CreateIssue;
import com.app.issue.dto.IssueView;
import com.app.issue.entity.Issue;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    public Issue create(CreateIssue createIssue);
    public List<IssueView> findAll();
    public Optional<IssueView> findById(Long id);
}
