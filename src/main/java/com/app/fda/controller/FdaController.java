package com.app.fda.controller;

import com.app.fda.dto.SearchRequest;
import com.app.fda.dto.SearchResponse;
import com.app.fda.exceptions.FdaBadRequestException;
import com.app.fda.exceptions.FdaNotFoundException;
import com.app.fda.service.FdaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/fdas")
public class FdaController {

    private final FdaService fdaService;

    public FdaController(FdaService fdaService) {
        this.fdaService = fdaService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchResponse> search(
            @RequestParam(name = "manufacturer_name") String manufacturerName,
            @RequestParam(name = "brand_name") Optional<String> brandName,
            @RequestParam(name = "limit", defaultValue = "10") long limit,
            @RequestParam(name = "offset", defaultValue = "0") long offset
    ) throws FdaNotFoundException, FdaBadRequestException {

        SearchResponse searchResult = this.fdaService.search(new SearchRequest(
                manufacturerName,
                brandName,
                limit,
                offset
        ));

        return new ResponseEntity<SearchResponse>(searchResult, HttpStatus.OK);
    }

}
