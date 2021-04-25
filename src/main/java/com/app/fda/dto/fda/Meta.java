package com.app.fda.dto.fda;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Meta {
    @SerializedName("disclaimer")
    private String disclaimer;
    @SerializedName("terms")
    private String terms;
    @SerializedName("license")
    private String license;
    @SerializedName("last_update")
    private String lastUpdate;
    @SerializedName("results")
    private PageInfo pageInfo;
}
