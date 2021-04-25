package com.app.issue.converter;

import com.app.issue.dto.IssueResponse;
import com.app.issue.entity.Issue;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IssueToIssueView implements Converter<Issue, IssueResponse> {

    @Override
    public IssueResponse convert(Issue issue) {
        return new IssueResponse(
                issue.getId(),
                issue.getManufacturerName(),
                issue.getSubstanceName(),
                issue.getProductNumbers()
        );
    }
}
