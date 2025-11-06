package com.swe.project.models.patient;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientRegistrationResponseDto {
    private String accessToken;
    private String refreshToken;
}
