package com.swe.project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SymptomDetailResponse {
    private Integer id;
    private String symptomName;
    private String description;
    private String tag;
}