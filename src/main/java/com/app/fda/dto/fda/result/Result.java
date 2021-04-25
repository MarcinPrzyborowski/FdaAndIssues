package com.app.fda.dto.fda.result;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Result {

    @SerializedName("application_number")
    private String applicationNumber;
    @SerializedName("sponsor_name")
    private String sponsorName;
    @SerializedName("openfda")
    private OpenFda openfda;
    @SerializedName("products")
    private List<Product> products;
    @SerializedName("submissions")
    private List<Submission> submissions;

}
