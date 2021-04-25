package com.app.fda.dto.fda.result;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OpenFda {

    @SerializedName("application_number")
    private List<String> applicationNumber;
    @SerializedName("brand_name")
    private List<String> brandName;
    @SerializedName("manufacturer_name")
    private List<String> manufacturerName;
    @SerializedName("generic_name")
    private List<String> genericName;
    @SerializedName("product_ndc")
    private List<String> productNdc;
    @SerializedName("product_type")
    private List<String> productType;
    @SerializedName("route")
    private List<String> route;
    @SerializedName("substance_name")
    private List<String> substanceName;
    @SerializedName("rxcui")
    private List<String> rxcui;
    @SerializedName("spl_id")
    private List<String> splId;
    @SerializedName("spl_set_id")
    private List<String> splSetId;
    @SerializedName("package_ndc")
    private List<String> packageNdc;
    @SerializedName("nui")
    private List<String> nui;
    @SerializedName("pharm_class_epc")
    private List<String> pharmClassEpc;
    @SerializedName("pharm_class_moa")
    private List<String> pharmClassMoa;
    @SerializedName("unii")
    private List<String> unii;

}
