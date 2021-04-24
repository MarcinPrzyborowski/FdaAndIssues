package com.app.issue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@Getter
public class CreateIssue {
    @NotNull
    private String manufacturerName;
    @NotNull
    private String substanceName;
    @NotEmpty
    private Set<Integer> productNumbers;

    public CreateIssue() {
    }
}
