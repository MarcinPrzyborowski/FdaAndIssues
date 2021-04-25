package com.app.fda.dto.fda.result;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Product {
    @SerializedName("product_number")
    private String productNumber;
    @SerializedName("reference_drug")
    private String referenceDrug;
    @SerializedName("brand_name")
    private String brandName;
    @SerializedName("active_ingredients")
    private List<ActiveIngredient> activeIngredients;
    @SerializedName("reference_standard")
    private String referenceStandard;
    @SerializedName("dosage_form")
    private String dosageForm;
    @SerializedName("route")
    private String route;
    @SerializedName("marketing_status")
    private String marketingStatus;
}
