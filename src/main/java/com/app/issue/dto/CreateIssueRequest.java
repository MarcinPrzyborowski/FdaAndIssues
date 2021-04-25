package com.app.issue.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateIssueRequest {
    @NotNull
    private String manufacturerName;
    @NotNull
    private String substanceName;
    @NotEmpty
    private Set<String> productNumbers;
}
