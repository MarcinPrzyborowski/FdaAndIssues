package com.app.issue.converter;

import com.app.issue.dto.CreateIssueRequest;
import com.app.issue.entity.Issue;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateIssueToIssue implements Converter<CreateIssueRequest, Issue> {

    @Override
    public Issue convert(CreateIssueRequest createIssueRequest) {

        return new Issue(
                createIssueRequest.getManufacturerName(),
                createIssueRequest.getSubstanceName(),
                createIssueRequest.getProductNumbers()
        );
    }
}
