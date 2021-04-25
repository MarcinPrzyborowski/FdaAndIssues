package com.app.fda.service;

import com.app.fda.dto.SearchRequest;
import com.app.fda.dto.SearchResponse;
import com.app.fda.dto.fda.result.Result;
import com.app.fda.exceptions.FdaBadRequestException;
import com.app.fda.exceptions.FdaNotFoundException;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class FdaServiceImplTest {


    @Mock
    RestTemplate restTemplate;
    Gson gson;
    FdaServiceImpl fdaService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        gson = new Gson();

        fdaService = new FdaServiceImpl(restTemplate, gson);
    }

    @Test(expected = FdaNotFoundException.class)
    public void searchWillThrowNotFoundException() throws FdaNotFoundException, FdaBadRequestException {
        SearchRequest searchRequest = new SearchRequest(
                "manufacturerName",
                Optional.empty(),
                10,
                10
        );

        when(restTemplate.getForObject(
                "https://api.fda.gov/drug/drugsfda.json?search=openfda.manufacturer_name:\"manufacturerName\"&limit=10&skip=10",
                String.class)
        ).thenThrow(HttpClientErrorException.create(HttpStatus.NOT_FOUND, "status-text", new HttpHeaders(), null, null));

        fdaService.search(searchRequest);
    }

    @Test(expected = FdaBadRequestException.class)
    public void searchWillThrowBadRequestException() throws FdaNotFoundException, FdaBadRequestException {
        SearchRequest searchRequest = new SearchRequest(
                "manufacturerName",
                Optional.empty(),
                10,
                10
        );

        when(restTemplate.getForObject(
                "https://api.fda.gov/drug/drugsfda.json?search=openfda.manufacturer_name:\"manufacturerName\"&limit=10&skip=10",
                String.class
                )
        ).thenThrow(HttpClientErrorException.create(HttpStatus.BAD_REQUEST, "status-text", new HttpHeaders(), null, null));

        fdaService.search(searchRequest);
    }

    @Test
    public void searchWillReturnValidObject() throws FdaNotFoundException, FdaBadRequestException {
        SearchRequest searchRequest = new SearchRequest(
                "manufacturerName",
                Optional.empty(),
                10,
                10
        );

        when(restTemplate.getForObject(
                "https://api.fda.gov/drug/drugsfda.json?search=openfda.manufacturer_name:\"manufacturerName\"&limit=10&skip=10",
                String.class
                )
        ).thenReturn("{\"meta\":{\"disclaimer\":\"DonotrelyonopenFDAtomakedecisionsregardingmedicalcare.Whilewemakeeveryefforttoensurethatdataisaccurate,youshouldassumeallresultsareunvalidated.WemaylimitorotherwiserestrictyouraccesstotheAPIinlinewithourTermsofService.\",\"terms\":\"https://open.fda.gov/terms/\",\"license\":\"https://open.fda.gov/license/\",\"last_updated\":\"2021-04-20\",\"results\":{\"skip\":0,\"limit\":1,\"total\":24795}},\"results\":[{\"submissions\":[{\"submission_type\":\"SUPPL\",\"submission_number\":\"5\",\"submission_status\":\"AP\",\"submission_status_date\":\"20191125\",\"review_priority\":\"STANDARD\",\"submission_class_code\":\"LABELING\",\"submission_class_code_description\":\"Labeling\",\"application_docs\":[{\"id\":\"61081\",\"url\":\"http://www.accessdata.fda.gov/drugsatfda_docs/appletter/2019/207145Orig1s005ltr.pdf\",\"date\":\"20191127\",\"type\":\"Letter\"},{\"id\":\"61100\",\"url\":\"http://www.accessdata.fda.gov/drugsatfda_docs/label/2019/207145s005lbl.pdf\",\"date\":\"20191129\",\"type\":\"Label\"}]},{\"submission_type\":\"SUPPL\",\"submission_number\":\"1\",\"submission_status\":\"AP\",\"submission_status_date\":\"20170616\",\"review_priority\":\"STANDARD\",\"submission_class_code\":\"LABELING\",\"submission_class_code_description\":\"Labeling\",\"application_docs\":[{\"id\":\"48756\",\"url\":\"http://www.accessdata.fda.gov/drugsatfda_docs/label/2017/207145s001lbl.pdf\",\"date\":\"20170619\",\"type\":\"Label\"},{\"id\":\"48775\",\"url\":\"http://www.accessdata.fda.gov/drugsatfda_docs/appletter/2017/207145Orig1s001ltr.pdf\",\"date\":\"20170620\",\"type\":\"Letter\"}]},{\"submission_type\":\"ORIG\",\"submission_number\":\"1\",\"submission_status\":\"AP\",\"submission_status_date\":\"20170321\",\"review_priority\":\"STANDARD\",\"submission_class_code\":\"TYPE1\",\"submission_class_code_description\":\"Type1-NewMolecularEntity\",\"application_docs\":[{\"id\":\"47630\",\"url\":\"http://www.accessdata.fda.gov/drugsatfda_docs/label/2017/207145lbl.pdf\",\"date\":\"20170322\",\"type\":\"Label\"},{\"id\":\"47730\",\"url\":\"http://www.accessdata.fda.gov/drugsatfda_docs/appletter/2017/207145Orig1s000ltr.pdf\",\"date\":\"20170328\",\"type\":\"Letter\"},{\"id\":\"48180\",\"url\":\"http://www.accessdata.fda.gov/drugsatfda_docs/nda/2017/207145Orig1s000TOC.cfm\",\"date\":\"20170501\",\"type\":\"Review\"}]}],\"application_number\":\"NDA207145\",\"sponsor_name\":\"MDDUS\",\"openfda\":{\"application_number\":[\"NDA207145\"],\"brand_name\":[\"XADAGO\"],\"generic_name\":[\"SAFINAMIDEMESYLATE\"],\"manufacturer_name\":[\"MDDUSOperations,LLC\"],\"product_ndc\":[\"27505-110\",\"27505-111\"],\"product_type\":[\"HUMANPRESCRIPTIONDRUG\"],\"route\":[\"ORAL\"],\"substance_name\":[\"SAFINAMIDEMESYLATE\"],\"rxcui\":[\"1922466\",\"1922472\",\"1922474\",\"1922476\"],\"spl_id\":[\"b72462f4-3b3a-d6eb-e053-2995a90a4f78\"],\"spl_set_id\":[\"c4d65f28-983f-42b4-bb23-023ae0fe81b2\"],\"package_ndc\":[\"27505-110-30\",\"27505-110-90\",\"27505-110-14\",\"27505-111-30\",\"27505-111-90\",\"27505-111-14\"],\"unii\":[\"YS90V3DTX0\"]},\"products\":[{\"product_number\":\"001\",\"reference_drug\":\"Yes\",\"brand_name\":\"XADAGO\",\"active_ingredients\":[{\"name\":\"SAFINAMIDEMESYLATE\",\"strength\":\"50MG\"}],\"reference_standard\":\"No\",\"dosage_form\":\"TABLET\",\"route\":\"ORAL\",\"marketing_status\":\"Prescription\"},{\"product_number\":\"002\",\"reference_drug\":\"Yes\",\"brand_name\":\"XADAGO\",\"active_ingredients\":[{\"name\":\"SAFINAMIDEMESYLATE\",\"strength\":\"100MG\"}],\"reference_standard\":\"Yes\",\"dosage_form\":\"TABLET\",\"route\":\"ORAL\",\"marketing_status\":\"Prescription\"}]}]}");

        SearchResponse searchResponse = fdaService.search(searchRequest);

        assertEquals(24795, searchResponse.getTotal());
        assertEquals(0, searchResponse.getOffset());
        assertEquals(1, searchResponse.getLimit());
        assertEquals(1, searchResponse.getItems().size());

        Result result = searchResponse.getItems().get(0);

        assertEquals("NDA207145", result.getApplicationNumber());
        assertEquals("MDDUS", result.getSponsorName());
        assertEquals(2, result.getProducts().size());
        assertEquals(3, result.getSubmissions().size());
    }
}

