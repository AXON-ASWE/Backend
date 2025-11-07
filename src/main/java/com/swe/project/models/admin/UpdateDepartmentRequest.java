package com.swe.project.models.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDepartmentRequest {
    private String departmentName;
    private String departmentDescription;
    private String departmentLocation;
}
