package com.app.fda.dto.fda.result;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ActiveIngredient {

    @SerializedName("name")
    private String name;
    @SerializedName("strength")
    private String strength;

}
