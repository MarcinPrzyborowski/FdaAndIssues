package com.app.issue.controller;

import com.app.issue.dto.CreateIssueRequest;
import com.app.issue.dto.IssueResponse;
import com.app.issue.entity.Issue;
import com.app.issue.service.IssueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IssueResponse>> getCollection() {
        return new ResponseEntity<>(this.issueService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IssueResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity.of(this.issueService.findById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Issue> create(@Valid @RequestBody CreateIssueRequest createIssueRequest) {
        final Issue issue = this.issueService.create(createIssueRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(issue.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
