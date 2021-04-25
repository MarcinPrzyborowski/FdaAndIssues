package com.app.fda.service;

import com.app.fda.dto.SearchRequest;
import com.app.fda.dto.SearchResponse;
import com.app.fda.dto.fda.DrugsFda;
import com.app.fda.dto.fda.PageInfo;
import com.app.fda.exceptions.FdaBadRequestException;
import com.app.fda.exceptions.FdaNotFoundException;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class FdaServiceImpl implements FdaService {

    private final String DEFAULT_URL = "https://api.fda.gov/drug/drugsfda.json?search=openfda.manufacturer_name:\"%s\"&limit=%d&skip=%d";
    private final String EXTENDED_URL = "https://api.fda.gov/drug/drugsfda.json?search=openfda.manufacturer_name:\"%s\"+AND+openfda.brand_name:\"%s\"&limit=%d&skip=%d";

    private final RestTemplate restTemplate;
    private final Gson gson;

    public FdaServiceImpl(RestTemplate restTemplate, Gson gson) {
        this.restTemplate = restTemplate;
        this.gson = gson;
    }

    public SearchResponse search(SearchRequest searchRequest) throws FdaNotFoundException, FdaBadRequestException {

        String searchUrl;

        if (searchRequest.getBrandName().isPresent()) {
            searchUrl = buildExtendedUrl(searchRequest);
        } else {
            searchUrl = buildDefaultUrl(searchRequest);
        }

        try {
            final String json = restTemplate.getForObject(searchUrl, String.class);

            DrugsFda drugsFda = gson.fromJson(json, DrugsFda.class);

            PageInfo pageInfo = drugsFda.getMeta().getPageInfo();

            return new SearchResponse(
                    pageInfo.getLimit(),
                    pageInfo.getOffset(),
                    pageInfo.getTotalCount(),
                    drugsFda.getResults()
            );
        } catch (HttpClientErrorException.NotFound exception) {
            throw new FdaNotFoundException();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new FdaBadRequestException();
        }
    }

    private String buildExtendedUrl(SearchRequest searchRequest) {
        return String.format(
                EXTENDED_URL,
                searchRequest.getManufacturerName(),
                searchRequest.getBrandName().get(),
                searchRequest.getLimit(),
                searchRequest.getOffset()
        );
    }

    private String buildDefaultUrl(SearchRequest searchRequest) {
        return String.format(
                DEFAULT_URL,
                searchRequest.getManufacturerName(),
                searchRequest.getLimit(),
                searchRequest.getOffset()
        );
    }

}
