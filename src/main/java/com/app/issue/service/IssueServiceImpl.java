package com.app.issue.service;

import com.app.issue.converter.CreateIssueToIssue;
import com.app.issue.converter.IssueToIssueView;
import com.app.issue.dto.CreateIssueRequest;
import com.app.issue.dto.IssueResponse;
import com.app.issue.entity.Issue;
import com.app.issue.repository.IssueRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final IssueToIssueView issueToIssueView;
    private final CreateIssueToIssue createIssueToIssue;

    public IssueServiceImpl(IssueRepository issueRepository,
                            IssueToIssueView issueToIssueView,
                            CreateIssueToIssue createIssueToIssue) {
        this.issueRepository = issueRepository;
        this.issueToIssueView = issueToIssueView;
        this.createIssueToIssue = createIssueToIssue;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Issue create(CreateIssueRequest createIssueRequest) {
        final Issue issue = createIssueToIssue.convert(Objects.requireNonNull(createIssueRequest));
        issueRepository.save(issue);

        return issue;
    }

    @Override
    public List<IssueResponse> findAll() {
        return issueRepository.findAll().stream().map(issueToIssueView::convert).collect(Collectors.toList());
    }

    @Override
    public Optional<IssueResponse> findById(Long id) {
        Optional<Issue> issue = issueRepository.findById(id);
        if (!issue.isPresent()) {
            return Optional.empty();
        }

        IssueResponse issueResponse = issueToIssueView.convert(issue.get());

        return Optional.of(issueResponse);
    }

}
