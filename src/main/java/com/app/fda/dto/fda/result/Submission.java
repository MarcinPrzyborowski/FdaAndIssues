package com.app.fda.dto.fda.result;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Submission {

    @SerializedName("submission_type")
    private String submissionType;
    @SerializedName("submission_number")
    private String submissionNumber;
    @SerializedName("submission_status")
    private String submissionStatus;
    @SerializedName("submission_status_date")
    private String submissionStatusDate;
    @SerializedName("review_priority")
    private String reviewPriority;
    @SerializedName("submission_class_code")
    private String submissionClassCode;
    @SerializedName("submission_class_code_description")
    private String submissionClassCodeDescription;

}
