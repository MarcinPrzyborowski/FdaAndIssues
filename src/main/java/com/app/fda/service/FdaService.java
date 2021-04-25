package com.app.fda.service;

import com.app.fda.dto.SearchRequest;
import com.app.fda.dto.SearchResponse;
import com.app.fda.exceptions.FdaBadRequestException;
import com.app.fda.exceptions.FdaNotFoundException;

public interface FdaService {
    SearchResponse search(SearchRequest searchRequest) throws FdaNotFoundException, FdaBadRequestException;
}
