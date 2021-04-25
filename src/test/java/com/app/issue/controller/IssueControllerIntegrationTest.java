package com.app.issue.controller;

import com.app.AppApplication;
import com.app.issue.service.IssueServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes = AppApplication.class)
public class IssueControllerIntegrationTest {

    @Autowired
    IssueServiceImpl issueService;

    IssueController issueController;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        issueController = new IssueController(issueService);
        mockMvc = MockMvcBuilders.standaloneSetup(issueController).build();
    }

    @Test
    public void getCollection() throws Exception {
        mockMvc.perform(get("/issues"))
                .andExpect(status().isOk());
    }

    @Test
    public void getById() throws Exception {
        mockMvc.perform(get("/issues/959595"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void create() throws Exception {
        String json = "{\"manufacturerName\":\"string\",\"substanceName\":\"string\",\"productNumbers\":[\"0\"]}";
        mockMvc.perform(
                post("/issues")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(status().isCreated()).andExpect(header().exists("location"));
    }

}
