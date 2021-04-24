package com.app.issue.converter;

import com.app.issue.dto.IssueView;
import com.app.issue.entity.Issue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.collections.Sets;

import java.util.Set;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;


public class IssueToIssueViewTest {

    IssueToIssueView converter;

    @Before
    public void setUp() {
        converter = new IssueToIssueView();
    }

    @Test(expected = NullPointerException.class)
    public void convertWithNullShouldThrowNullPointerException() throws Exception {
       converter.convert(null);
    }

    @Test
    public void convertWithValidParametersWillCreateNewIssue() {
        Set<Integer> productNumbers = Sets.newSet(1,2,3);

        Issue issue = mock(Issue.class);

        when(issue.getId()).thenReturn(1L);
        when(issue.getManufacturerName()).thenReturn("Name");
        when(issue.getSubstanceName()).thenReturn("SubstanceName");
        when(issue.getProductNumbers()).thenReturn(productNumbers);

        IssueView issueView = converter.convert(issue);

        assertSame(1L, issueView.getId());
        assertSame("Name", issueView.getManufacturerName());
        assertSame("SubstanceName", issueView.getSubstanceName());
        assertSame(productNumbers, issueView.getProductNumbers());
    }
}