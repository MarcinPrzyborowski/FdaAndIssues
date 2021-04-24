package com.app.issue.converter;

import com.app.issue.dto.IssueView;
import com.app.issue.entity.Issue;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IssueToIssueView implements Converter<Issue, IssueView> {

    @Override
    public IssueView convert(Issue issue) {
        return new IssueView(
                issue.getId(),
                issue.getManufacturerName(),
                issue.getSubstanceName(),
                issue.getProductNumbers()
        );
    }
}
