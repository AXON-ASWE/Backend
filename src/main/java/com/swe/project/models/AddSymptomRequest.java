package com.swe.project.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddSymptomRequest {
    private String symptomName;
    private String description;
    private String tag;
}