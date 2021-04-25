package com.app.issue.converter;

import com.app.issue.dto.IssueResponse;
import com.app.issue.entity.Issue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.util.Set;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class IssueToIssueResponseTest {

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
        Set<String> productNumbers = Sets.newSet("1", "2", "3");

        Issue issue = mock(Issue.class);

        when(issue.getId()).thenReturn(1L);
        when(issue.getManufacturerName()).thenReturn("Name");
        when(issue.getSubstanceName()).thenReturn("SubstanceName");
        when(issue.getProductNumbers()).thenReturn(productNumbers);

        IssueResponse issueResponse = converter.convert(issue);

        assertSame(1L, issueResponse.getId());
        assertSame("Name", issueResponse.getManufacturerName());
        assertSame("SubstanceName", issueResponse.getSubstanceName());
        assertSame(productNumbers, issueResponse.getProductNumbers());
    }
}