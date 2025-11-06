package com.swe.project.services.patientService;

import com.swe.project.models.authentication.AuthenticationResponse;
import com.swe.project.models.patient.PatientRegistrationDto;

public interface PatientAuthenticationService {
    void registerPatient(PatientRegistrationDto registrationDto);
    AuthenticationResponse activatePatientAccount(String email);
}
