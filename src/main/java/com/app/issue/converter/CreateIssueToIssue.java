package com.app.issue.converter;

import com.app.issue.dto.CreateIssue;
import com.app.issue.entity.Issue;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CreateIssueToIssue implements Converter<CreateIssue, Issue> {

    @Override
    public Issue convert(CreateIssue createIssue) {

        return new Issue(
                createIssue.getManufacturerName(),
                createIssue.getSubstanceName(),
                createIssue.getProductNumbers()
        );
    }
}
