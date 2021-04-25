package com.app.fda.dto.fda;

import com.app.fda.dto.fda.result.Result;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class DrugsFda {
    @SerializedName("meta")
    private Meta meta;
    @SerializedName("results")
    private List<Result> results;
}

