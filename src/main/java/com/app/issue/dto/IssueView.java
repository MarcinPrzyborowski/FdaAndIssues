package com.app.issue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class IssueView {
    private final Long id;
    private final String manufacturerName;
    private final String substanceName;
    private final Set<Integer> productNumbers;
}
