package com.swe.project.services.patientService;

import com.swe.project.models.patient.PatientRegistrationDto;
import com.swe.project.models.patient.PatientRegistrationResponseDto;

public interface PatientAuthenticationService {
    PatientRegistrationResponseDto registerPatient(PatientRegistrationDto registrationDto);
}
