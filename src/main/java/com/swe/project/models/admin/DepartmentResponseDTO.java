package com.swe.project.models.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponseDTO {
    Integer departmentId;
    String departmentName;
    String departmentDescription;
    String departmentLocation;
}
