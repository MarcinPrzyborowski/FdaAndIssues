package com.app.fda.dto.fda;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageInfo {
    @SerializedName("skip")
    private int offset;
    @SerializedName("limit")
    private int limit;
    @SerializedName("total")
    private int totalCount;
}
