package com.app.issue.service;

import com.app.issue.converter.CreateIssueToIssue;
import com.app.issue.converter.IssueToIssueView;
import com.app.issue.dto.CreateIssue;
import com.app.issue.dto.IssueView;
import com.app.issue.entity.Issue;
import com.app.issue.repository.IssueRepository;
import org.springframework.stereotype.Service;

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
    public Issue create(CreateIssue createIssue) {
        final Issue issue = createIssueToIssue.convert(Objects.requireNonNull(createIssue));
        issueRepository.save(issue);

        return issue;
    }

    @Override
    public List<IssueView> findAll() {
        return issueRepository.findAll().stream().map(issueToIssueView::convert).collect(Collectors.toList());
    }

    @Override
    public Optional<IssueView> findById(Long id) {
        Optional<Issue> issue = issueRepository.findById(id);
        if (!issue.isPresent()) {
            return Optional.empty();
        }

        IssueView issueView = issueToIssueView.convert(issue.get());

        return Optional.of(issueView);
    }

}
