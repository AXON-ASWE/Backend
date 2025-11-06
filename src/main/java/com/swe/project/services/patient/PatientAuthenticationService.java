package com.swe.project.services.patient;

import com.swe.project.models.authentication.AuthenticationResponse;
import com.swe.project.models.patient.PatientRegistrationDto;

public interface PatientAuthenticationService {
    AuthenticationResponse registerPatient(PatientRegistrationDto registrationDto);
}
