package com.app.issue.service;

import com.app.issue.converter.CreateIssueToIssue;
import com.app.issue.converter.IssueToIssueView;
import com.app.issue.dto.CreateIssue;
import com.app.issue.dto.IssueView;
import com.app.issue.entity.Issue;
import com.app.issue.repository.IssueRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        CreateIssue createIssue = Mockito.mock(CreateIssue.class);
        Issue issue = Mockito.mock(Issue.class);
        when(createIssueToIssue.convert(createIssue)).thenReturn(issue);

        Issue createdIssue = issueService.create(createIssue);

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

        List<IssueView> issueViews = issueService.findAll();
        assertEquals(1, issueViews.size());

        verify(issueToIssueView, times(1)).convert(issue);
    }

    @Test
    public void findAllWillReturnEmptyList() {
        when(issueRepository.findAll()).thenReturn(new ArrayList<>());

        List<IssueView> issueViews = issueService.findAll();
        assertTrue(issueViews.isEmpty());

        verify(issueToIssueView, times(0)).convert(any());
    }

    @Test
    public void findByIdWillReturnEmpty() {
        when(issueRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<IssueView> result = issueService.findById(1L);
        assertFalse(result.isPresent());
    }

    public Optional<IssueView> findByIde(Long id) {
        Optional<Issue> issue = issueRepository.findById(id);
        if (!issue.isPresent()) {
            return Optional.empty();
        }

        IssueView issueView = issueToIssueView.convert(issue.get());

        return Optional.of(issueView);
    }

    @Test
    public void findByIdWillReturnIssueView() {
        Issue issue = mock(Issue.class);
        Optional<Issue> optional = Optional.of(issue);
        when(issueRepository.findById(1L)).thenReturn(optional);

        IssueView issueView = mock(IssueView.class);
        when(issueToIssueView.convert(optional.get())).thenReturn(issueView);

        Optional<IssueView> optionalIssueView = issueService.findById(1L);

        assertTrue(optionalIssueView.isPresent());
        assertSame(issueView, optionalIssueView.get());

    }

}
