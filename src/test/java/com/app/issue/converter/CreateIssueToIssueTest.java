package com.app.issue.converter;

import com.app.issue.dto.CreateIssue;
import com.app.issue.entity.Issue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.util.Set;

import static org.junit.Assert.*;

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
        Set<Integer> productNumbers = Sets.newSet(1,2,3);
        CreateIssue createIssue = new CreateIssue(
                "Name",
                "SubstanceName",
                productNumbers
        );

        Issue issue = converter.convert(createIssue);

        assertSame("Name", issue.getManufacturerName());
        assertSame("SubstanceName", issue.getSubstanceName());
        assertSame(productNumbers, issue.getProductNumbers());
    }
}