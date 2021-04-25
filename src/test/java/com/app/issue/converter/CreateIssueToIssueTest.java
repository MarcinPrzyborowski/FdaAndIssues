package com.app.issue.converter;

import com.app.issue.dto.CreateIssueRequest;
import com.app.issue.entity.Issue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.util.Set;

import static org.junit.Assert.assertSame;

public class CreateIssueToIssueTest {

    CreateIssueToIssue converter;

    @Before
    public void setUp() {
        converter = new CreateIssueToIssue();
    }

    @Test(expected = NullPointerException.class)
    public void convertWithNullShouldThrowNullPointerException() throws Exception {
       converter.convert(null);
    }

    @Test
    public void convertWithValidParametersWillCreateNewIssue() {
        Set<String> productNumbers = Sets.newSet("1", "2", "3");
        CreateIssueRequest createIssueRequest = new CreateIssueRequest(
                "Name",
                "SubstanceName",
                productNumbers
        );

        Issue issue = converter.convert(createIssueRequest);

        assertSame("Name", issue.getManufacturerName());
        assertSame("SubstanceName", issue.getSubstanceName());
        assertSame(productNumbers, issue.getProductNumbers());
    }
}