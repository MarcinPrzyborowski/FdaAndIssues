package com.app.issue.service;

import com.app.issue.converter.CreateIssueToIssue;
import com.app.issue.converter.IssueToIssueView;
import com.app.issue.dto.CreateIssueRequest;
import com.app.issue.dto.IssueResponse;
import com.app.issue.entity.Issue;
import com.app.issue.repository.IssueRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class IssueServiceImplTest {

    @Mock
    IssueRepository issueRepository;

    @Mock
    IssueToIssueView issueToIssueView;

    @Mock
    CreateIssueToIssue createIssueToIssue;

    IssueServiceImpl issueService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        issueService = new IssueServiceImpl(issueRepository, issueToIssueView, createIssueToIssue);
    }

    @Test
    public void createWithValidObjectWillCreateAndReturnNewObject() {
        CreateIssueRequest createIssueRequest = mock(CreateIssueRequest.class);
        Issue issue = mock(Issue.class);
        when(createIssueToIssue.convert(createIssueRequest)).thenReturn(issue);

        Issue createdIssue = issueService.create(createIssueRequest);

        assertSame(issue, createdIssue);

        verify(issueRepository, times(1)).save(issue);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateWithNullWillThrowNullPointerException() {
        issueService.create(null);
    }

    @Test
    public void findAllWillReturnFilledList() {
        Issue issue = mock(Issue.class);
        List<Issue> issues = Lists.newArrayList(issue);
        when(issueRepository.findAll()).thenReturn(issues);

        List<IssueResponse> issueResponses = issueService.findAll();
        assertEquals(1, issueResponses.size());

        verify(issueToIssueView, times(1)).convert(issue);
    }

    @Test
    public void findAllWillReturnEmptyList() {
        when(issueRepository.findAll()).thenReturn(new ArrayList<>());

        List<IssueResponse> issueResponses = issueService.findAll();
        assertTrue(issueResponses.isEmpty());

        verify(issueToIssueView, times(0)).convert(any());
    }

    @Test
    public void findByIdWillReturnEmpty() {
        when(issueRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<IssueResponse> result = issueService.findById(1L);
        assertFalse(result.isPresent());
    }

    public Optional<IssueResponse> findByIde(Long id) {
        Optional<Issue> issue = issueRepository.findById(id);
        if (!issue.isPresent()) {
            return Optional.empty();
        }

        IssueResponse issueResponse = issueToIssueView.convert(issue.get());

        return Optional.of(issueResponse);
    }

    @Test
    public void findByIdWillReturnIssueView() {
        Issue issue = mock(Issue.class);
        Optional<Issue> optional = Optional.of(issue);
        when(issueRepository.findById(1L)).thenReturn(optional);

        IssueResponse issueResponse = mock(IssueResponse.class);
        when(issueToIssueView.convert(optional.get())).thenReturn(issueResponse);

        Optional<IssueResponse> optionalIssueView = issueService.findById(1L);

        assertTrue(optionalIssueView.isPresent());
        assertSame(issueResponse, optionalIssueView.get());
    }

}
