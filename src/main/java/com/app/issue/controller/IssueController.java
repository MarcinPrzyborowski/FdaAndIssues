package com.app.issue.controller;

import com.app.issue.dto.CreateIssue;
import com.app.issue.dto.IssueView;
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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<IssueView>> getCollection() {
        return new ResponseEntity<>(this.issueService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<IssueView> getById(@PathVariable("id") Long id) {
        return ResponseEntity.of(this.issueService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Issue> create(@Valid @RequestBody CreateIssue createIssue) {
        Issue issue = this.issueService.create(createIssue);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(issue.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
