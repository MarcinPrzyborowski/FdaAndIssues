package com.app.fda.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@AllArgsConstructor
@Getter
public class SearchRequest {
    private final String manufacturerName;
    private final Optional<String> brandName;
    private final long limit;
    private final long offset;
}
