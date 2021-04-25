package com.app.issue.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String manufacturerName;

    private String substanceName;

    @ElementCollection
    private Set<String> productNumbers;

    public Issue() {
    }

    public Issue(String manufacturerName, String substanceName, Set<String> productNumbers) {
        this.manufacturerName = manufacturerName;
        this.substanceName = substanceName;
        this.productNumbers = productNumbers;
    }
}
