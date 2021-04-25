package com.app.fda.dto;

import com.app.fda.dto.fda.result.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class SearchResponse {
    private final long limit;
    private final long offset;
    private final long total;
    private final List<Result> items;

    public static SearchResponse empty() {
        return new SearchResponse(0, 0, 0, new ArrayList<>());
    }

}
