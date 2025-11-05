-- ============================================
-- MOCK DATA FOR AXON PROJECT DATABASE
-- ============================================

-- Insert Admins (T_ADMINS - extends Users)
INSERT INTO T_ADMINS (EMAIL, PASSWORD_HASH, FULL_NAME, PHONE, GENDER, DATE_OF_BIRTH, ROLE, STATUS, CREATED_AT) VALUES
('admin@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'System Administrator', '555-9999', 'Other', '1985-01-15', 'ADMIN', 'ACTIVE', '2024-01-01');

-- Insert Patients (T_PATIENTS - extends Users)
INSERT INTO T_PATIENTS (EMAIL, PASSWORD_HASH, FULL_NAME, PHONE, GENDER, DATE_OF_BIRTH, ROLE, STATUS, CREATED_AT, ADDRESS, EMERGENCY_CONTACT, INSURANCE_NUMBER) VALUES
('john.doe@email.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'John Doe', '555-0201', 'Male', '1980-05-20', 'PATIENT', 'ACTIVE', '2024-01-15', '123 Main St, New York, NY 10001', '555-1001', 'INS-001-2024'),
('jane.smith@email.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Jane Smith', '555-0202', 'Female', '1992-08-15', 'PATIENT', 'ACTIVE', '2024-01-16', '456 Oak Ave, Los Angeles, CA 90001', '555-1002', 'INS-002-2024'),
('bob.wilson@email.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Bob Wilson', '555-0203', 'Male', '1988-03-10', 'PATIENT', 'ACTIVE', '2024-01-17', '789 Pine Rd, Chicago, IL 60601', '555-1003', 'INS-003-2024'),
('alice.brown@email.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Alice Brown', '555-0204', 'Female', '1995-11-25', 'PATIENT', 'ACTIVE', '2024-01-18', '321 Elm St, Houston, TX 77001', '555-1004', 'INS-004-2024'),
('charlie.davis@email.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Charlie Davis', '555-0205', 'Male', '1975-07-08', 'PATIENT', 'ACTIVE', '2024-01-19', '654 Maple Dr, Phoenix, AZ 85001', '555-1005', 'INS-005-2024');

-- Insert Symptoms (T_SYMPTOM)
INSERT INTO T_SYMPTOM (SYMPTOM_NAME, DESCRIPTION, TAG) VALUES
('Fever', 'High body temperature', 'common'),
('Headache', 'Pain in head region', 'common'),
('Chest Pain', 'Discomfort in chest area', 'urgent'),
('Shortness of Breath', 'Difficulty breathing', 'urgent'),
('Cough', 'Persistent coughing', 'common'),
('Fatigue', 'Extreme tiredness', 'common'),
('Nausea', 'Feeling of sickness', 'common'),
('Dizziness', 'Feeling lightheaded', 'common'),
('Abdominal Pain', 'Pain in stomach area', 'moderate'),
('Back Pain', 'Pain in back region', 'moderate'),
('Joint Pain', 'Pain in joints', 'moderate'),
('Skin Rash', 'Unusual skin condition', 'moderate'),
('Anxiety', 'Feeling of worry or unease', 'mental'),
('Depression', 'Persistent sadness', 'mental'),
('Toothache', 'Pain in teeth or gums', 'dental'),
('Blurred Vision', 'Unclear vision', 'eye'),
('Ear Pain', 'Pain in ear', 'ent'),
('Frequent Urination', 'Need to urinate often', 'urological'),
('Irregular Periods', 'Abnormal menstrual cycle', 'gynecological'),
('Memory Loss', 'Difficulty remembering', 'neurological');

-- Insert Departments (T_DEPARTMENT)
INSERT INTO T_DEPARTMENT (DEPARTMENT_NAME, DESCRIPTION, LOCATION) VALUES
('Cardiology', 'Heart and cardiovascular system care', 'Building A, Floor 3'),
('Neurology', 'Brain and nervous system disorders', 'Building A, Floor 4'),
('Orthopedics', 'Bone, joint, and muscle treatment', 'Building B, Floor 2'),
('Pediatrics', 'Child healthcare', 'Building C, Floor 1'),
('Dermatology', 'Skin conditions and treatment', 'Building B, Floor 3'),
('Psychiatry', 'Mental health care', 'Building C, Floor 2'),
('General Medicine', 'General health consultation', 'Building A, Floor 1'),
('Emergency', 'Emergency medical care', 'Building A, Ground Floor'),
('Dentistry', 'Dental care and oral health', 'Building B, Floor 1'),
('Ophthalmology', 'Eye care and vision', 'Building C, Floor 3'),
('ENT', 'Ear, Nose, and Throat care', 'Building B, Floor 4'),
('Urology', 'Urinary system and male reproductive organs', 'Building A, Floor 5'),
('Gynecology', 'Women health and reproductive system', 'Building C, Floor 4'),
('Gastroenterology', 'Digestive system disorders', 'Building A, Floor 2');

-- Link Departments with Symptoms (T_DEPARTMENT_SYMPTOM)
-- Cardiology symptoms (Chest Pain, Shortness of Breath, Fatigue, Dizziness)
INSERT INTO T_DEPARTMENT_SYMPTOM (DEPARTMENT_ID, SYMPTOM_ID) VALUES
(1, 3), (1, 4), (1, 6), (1, 8);

-- Neurology symptoms (Headache, Dizziness, Memory Loss)
INSERT INTO T_DEPARTMENT_SYMPTOM (DEPARTMENT_ID, SYMPTOM_ID) VALUES
(2, 2), (2, 8), (2, 20);

-- Orthopedics symptoms (Back Pain, Joint Pain)
INSERT INTO T_DEPARTMENT_SYMPTOM (DEPARTMENT_ID, SYMPTOM_ID) VALUES
(3, 10), (3, 11);

-- Pediatrics symptoms (Fever, Cough, Nausea)
INSERT INTO T_DEPARTMENT_SYMPTOM (DEPARTMENT_ID, SYMPTOM_ID) VALUES
(4, 1), (4, 5), (4, 7);

-- Dermatology symptoms (Skin Rash)
INSERT INTO T_DEPARTMENT_SYMPTOM (DEPARTMENT_ID, SYMPTOM_ID) VALUES
(5, 12);

-- Psychiatry symptoms (Anxiety, Depression)
INSERT INTO T_DEPARTMENT_SYMPTOM (DEPARTMENT_ID, SYMPTOM_ID) VALUES
(6, 13), (6, 14);

-- General Medicine symptoms (Fever, Headache, Cough, Fatigue, Nausea)
INSERT INTO T_DEPARTMENT_SYMPTOM (DEPARTMENT_ID, SYMPTOM_ID) VALUES
(7, 1), (7, 2), (7, 5), (7, 6), (7, 7);

-- Emergency symptoms (Chest Pain, Shortness of Breath, Dizziness)
INSERT INTO T_DEPARTMENT_SYMPTOM (DEPARTMENT_ID, SYMPTOM_ID) VALUES
(8, 3), (8, 4), (8, 8);

-- Dentistry symptoms (Toothache)
INSERT INTO T_DEPARTMENT_SYMPTOM (DEPARTMENT_ID, SYMPTOM_ID) VALUES
(9, 15);

-- Ophthalmology symptoms (Blurred Vision)
INSERT INTO T_DEPARTMENT_SYMPTOM (DEPARTMENT_ID, SYMPTOM_ID) VALUES
(10, 16);

-- ENT symptoms (Ear Pain)
INSERT INTO T_DEPARTMENT_SYMPTOM (DEPARTMENT_ID, SYMPTOM_ID) VALUES
(11, 17);

-- Urology symptoms (Frequent Urination)
INSERT INTO T_DEPARTMENT_SYMPTOM (DEPARTMENT_ID, SYMPTOM_ID) VALUES
(12, 18);

-- Gynecology symptoms (Irregular Periods)
INSERT INTO T_DEPARTMENT_SYMPTOM (DEPARTMENT_ID, SYMPTOM_ID) VALUES
(13, 19);

-- Gastroenterology symptoms (Nausea, Abdominal Pain)
INSERT INTO T_DEPARTMENT_SYMPTOM (DEPARTMENT_ID, SYMPTOM_ID) VALUES
(14, 7), (14, 9);

-- Insert Doctors (T_DOCTOR - extends Users)
-- Cardiology
INSERT INTO T_DOCTOR (EMAIL, PASSWORD_HASH, FULL_NAME, PHONE, GENDER, DATE_OF_BIRTH, ROLE, STATUS, CREATED_AT, SPECIALIZATION, EXPERIENCE_YEARS, QUALIFICATIONS, DEPARTMENT_ID) VALUES
('michael.chen@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Michael Chen', '555-0101', 'Male', '1975-03-12', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Cardiology', 15, 'MD, FACC', 1),
('sarah.johnson@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Sarah Johnson', '555-0102', 'Female', '1978-09-25', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Cardiology', 12, 'MD, FACC', 1);

-- Neurology
INSERT INTO T_DOCTOR (EMAIL, PASSWORD_HASH, FULL_NAME, PHONE, GENDER, DATE_OF_BIRTH, ROLE, STATUS, CREATED_AT, SPECIALIZATION, EXPERIENCE_YEARS, QUALIFICATIONS, DEPARTMENT_ID) VALUES
('robert.williams@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Robert Williams', '555-0103', 'Male', '1970-06-15', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Neurology', 20, 'MD, PhD, FAAN', 2),
('emily.davis@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Emily Davis', '555-0104', 'Female', '1982-12-08', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Neurology', 8, 'MD, FAAN', 2);

-- Orthopedics
INSERT INTO T_DOCTOR (EMAIL, PASSWORD_HASH, FULL_NAME, PHONE, GENDER, DATE_OF_BIRTH, ROLE, STATUS, CREATED_AT, SPECIALIZATION, EXPERIENCE_YEARS, QUALIFICATIONS, DEPARTMENT_ID) VALUES
('james.martinez@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. James Martinez', '555-0105', 'Male', '1972-04-22', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Orthopedics', 18, 'MD, FAAOS', 3),
('lisa.anderson@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Lisa Anderson', '555-0106', 'Female', '1980-11-30', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Orthopedics', 10, 'MD, FAAOS', 3);

-- Pediatrics
INSERT INTO T_DOCTOR (EMAIL, PASSWORD_HASH, FULL_NAME, PHONE, GENDER, DATE_OF_BIRTH, ROLE, STATUS, CREATED_AT, SPECIALIZATION, EXPERIENCE_YEARS, QUALIFICATIONS, DEPARTMENT_ID) VALUES
('david.thompson@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. David Thompson', '555-0107', 'Male', '1976-08-14', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Pediatrics', 14, 'MD, FAAP', 4),
('jennifer.white@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Jennifer White', '555-0108', 'Female', '1983-02-18', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Pediatrics', 9, 'MD, FAAP', 4);

-- Dermatology
INSERT INTO T_DOCTOR (EMAIL, PASSWORD_HASH, FULL_NAME, PHONE, GENDER, DATE_OF_BIRTH, ROLE, STATUS, CREATED_AT, SPECIALIZATION, EXPERIENCE_YEARS, QUALIFICATIONS, DEPARTMENT_ID) VALUES
('christopher.lee@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Christopher Lee', '555-0109', 'Male', '1979-05-27', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Dermatology', 11, 'MD, FAAD', 5),
('amanda.garcia@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Amanda Garcia', '555-0110', 'Female', '1985-10-03', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Dermatology', 7, 'MD, FAAD', 5);

-- Psychiatry
INSERT INTO T_DOCTOR (EMAIL, PASSWORD_HASH, FULL_NAME, PHONE, GENDER, DATE_OF_BIRTH, ROLE, STATUS, CREATED_AT, SPECIALIZATION, EXPERIENCE_YEARS, QUALIFICATIONS, DEPARTMENT_ID) VALUES
('daniel.rodriguez@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Daniel Rodriguez', '555-0111', 'Male', '1974-07-19', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Psychiatry', 16, 'MD, Board Certified', 6),
('patricia.martinez@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Patricia Martinez', '555-0112', 'Female', '1977-01-24', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Psychiatry', 13, 'MD, Board Certified', 6);

-- General Medicine
INSERT INTO T_DOCTOR (EMAIL, PASSWORD_HASH, FULL_NAME, PHONE, GENDER, DATE_OF_BIRTH, ROLE, STATUS, CREATED_AT, SPECIALIZATION, EXPERIENCE_YEARS, QUALIFICATIONS, DEPARTMENT_ID) VALUES
('kevin.brown@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Kevin Brown', '555-0113', 'Male', '1980-09-11', 'DOCTOR', 'ACTIVE', '2024-01-01', 'General Medicine', 10, 'MD, ABIM', 7),
('michelle.wilson@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Michelle Wilson', '555-0114', 'Female', '1984-04-16', 'DOCTOR', 'ACTIVE', '2024-01-01', 'General Medicine', 8, 'MD, ABIM', 7);

-- Emergency
INSERT INTO T_DOCTOR (EMAIL, PASSWORD_HASH, FULL_NAME, PHONE, GENDER, DATE_OF_BIRTH, ROLE, STATUS, CREATED_AT, SPECIALIZATION, EXPERIENCE_YEARS, QUALIFICATIONS, DEPARTMENT_ID) VALUES
('steven.taylor@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Steven Taylor', '555-0115', 'Male', '1978-11-09', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Emergency Medicine', 12, 'MD, ABEM', 8),
('laura.thomas@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Laura Thomas', '555-0116', 'Female', '1983-06-28', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Emergency Medicine', 9, 'MD, ABEM', 8);

-- Dentistry
INSERT INTO T_DOCTOR (EMAIL, PASSWORD_HASH, FULL_NAME, PHONE, GENDER, DATE_OF_BIRTH, ROLE, STATUS, CREATED_AT, SPECIALIZATION, EXPERIENCE_YEARS, QUALIFICATIONS, DEPARTMENT_ID) VALUES
('brian.moore@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Brian Moore', '555-0117', 'Male', '1975-02-14', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Dentistry', 15, 'DDS', 9),
('karen.jackson@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Karen Jackson', '555-0118', 'Female', '1979-08-21', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Dentistry', 11, 'DDS', 9);

-- Ophthalmology
INSERT INTO T_DOCTOR (EMAIL, PASSWORD_HASH, FULL_NAME, PHONE, GENDER, DATE_OF_BIRTH, ROLE, STATUS, CREATED_AT, SPECIALIZATION, EXPERIENCE_YEARS, QUALIFICATIONS, DEPARTMENT_ID) VALUES
('richard.martin@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Richard Martin', '555-0119', 'Male', '1973-12-05', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Ophthalmology', 17, 'MD, FACS', 10),
('nancy.lee@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Nancy Lee', '555-0120', 'Female', '1980-03-17', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Ophthalmology', 10, 'MD, FACS', 10);

-- ENT
INSERT INTO T_DOCTOR (EMAIL, PASSWORD_HASH, FULL_NAME, PHONE, GENDER, DATE_OF_BIRTH, ROLE, STATUS, CREATED_AT, SPECIALIZATION, EXPERIENCE_YEARS, QUALIFICATIONS, DEPARTMENT_ID) VALUES
('paul.harris@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Paul Harris', '555-0121', 'Male', '1976-07-23', 'DOCTOR', 'ACTIVE', '2024-01-01', 'ENT', 14, 'MD, FACS', 11),
('sandra.clark@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Sandra Clark', '555-0122', 'Female', '1983-01-09', 'DOCTOR', 'ACTIVE', '2024-01-01', 'ENT', 9, 'MD, FACS', 11);

-- Urology
INSERT INTO T_DOCTOR (EMAIL, PASSWORD_HASH, FULL_NAME, PHONE, GENDER, DATE_OF_BIRTH, ROLE, STATUS, CREATED_AT, SPECIALIZATION, EXPERIENCE_YEARS, QUALIFICATIONS, DEPARTMENT_ID) VALUES
('mark.lewis@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Mark Lewis', '555-0123', 'Male', '1971-10-30', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Urology', 19, 'MD, FACS', 12),
('barbara.walker@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Barbara Walker', '555-0124', 'Female', '1978-05-12', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Urology', 12, 'MD, FACS', 12);

-- Gynecology
INSERT INTO T_DOCTOR (EMAIL, PASSWORD_HASH, FULL_NAME, PHONE, GENDER, DATE_OF_BIRTH, ROLE, STATUS, CREATED_AT, SPECIALIZATION, EXPERIENCE_YEARS, QUALIFICATIONS, DEPARTMENT_ID) VALUES
('thomas.hall@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Thomas Hall', '555-0125', 'Male', '1974-09-06', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Gynecology', 16, 'MD, FACOG', 13),
('elizabeth.allen@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Elizabeth Allen', '555-0126', 'Female', '1979-04-20', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Gynecology', 11, 'MD, FACOG', 13);

-- Gastroenterology
INSERT INTO T_DOCTOR (EMAIL, PASSWORD_HASH, FULL_NAME, PHONE, GENDER, DATE_OF_BIRTH, ROLE, STATUS, CREATED_AT, SPECIALIZATION, EXPERIENCE_YEARS, QUALIFICATIONS, DEPARTMENT_ID) VALUES
('joseph.young@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Joseph Young', '555-0127', 'Male', '1977-11-14', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Gastroenterology', 13, 'MD, FACG', 14),
('susan.king@hospital.com', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6', 'Dr. Susan King', '555-0128', 'Female', '1980-06-08', 'DOCTOR', 'ACTIVE', '2024-01-01', 'Gastroenterology', 10, 'MD, FACG', 14);

-- Insert Appointments (T_APPOINTMENT)
-- Note: Patient IDs and Doctor IDs will be auto-generated. 
-- Assuming sequential IDs: Patients 2-6, Doctors 7-34 (after 1 admin and 5 patients)
-- For safety, use actual generated IDs after first run or adjust accordingly

-- Upcoming appointments
INSERT INTO T_APPOINTMENT (PATIENT_ID, DOCTOR_ID, APPOINTMENT_DATE, TIME_SLOT, STATUS, NOTES, CREATED_AT) VALUES
-- Patient 1 (John Doe, USER_ID should be 2) with Dr. Michael Chen (USER_ID should be 7)
(2, 7, '2025-11-05', 5, 'SCHEDULED', 'Regular cardiology checkup', '2025-10-28 10:00:00'),
-- Patient 2 (Jane Smith, USER_ID should be 3) with Dr. David Thompson (USER_ID should be 13)
(3, 13, '2025-11-06', 7, 'SCHEDULED', 'Asthma follow-up', '2025-10-29 14:30:00'),
-- Patient 3 (Bob Wilson, USER_ID should be 4) with Dr. Kevin Brown (USER_ID should be 19)
(4, 19, '2025-11-07', 11, 'SCHEDULED', 'General health consultation', '2025-10-30 09:15:00'),
-- Patient 4 (Alice Brown, USER_ID should be 5) with Dr. James Martinez (USER_ID should be 11)
(5, 11, '2025-11-08', 8, 'SCHEDULED', 'Joint pain examination', '2025-10-31 16:20:00'),
-- Patient 4 (Alice Brown, USER_ID should be 5) with Dr. Michael Chen (USER_ID should be 7)
(5, 7, '2025-11-10', 13, 'SCHEDULED', 'Post-surgery follow-up', '2025-11-01 08:00:00');

-- Past appointments (completed)
INSERT INTO T_APPOINTMENT (PATIENT_ID, DOCTOR_ID, APPOINTMENT_DATE, TIME_SLOT, STATUS, NOTES, CREATED_AT) VALUES
(2, 19, '2025-10-20', 5, 'COMPLETED', 'Annual checkup - all clear', '2025-10-15 11:00:00'),
(3, 9, '2025-10-22', 8, 'COMPLETED', 'Headache consultation', '2025-10-18 13:30:00'),
(4, 20, '2025-10-25', 9, 'COMPLETED', 'Fever and cold', '2025-10-20 10:00:00'),
(5, 15, '2025-10-28', 12, 'COMPLETED', 'Skin rash treatment', '2025-10-23 15:45:00'),
(2, 8, '2025-10-30', 8, 'COMPLETED', 'Cardiac monitoring', '2025-10-25 09:30:00');

-- Cancelled appointments
INSERT INTO T_APPOINTMENT (PATIENT_ID, DOCTOR_ID, APPOINTMENT_DATE, TIME_SLOT, STATUS, NOTES, CREATED_AT) VALUES
(3, 10, '2025-11-01', 6, 'CANCELLED', 'Patient requested cancellation', '2025-10-26 12:00:00'),
(2, 10, '2025-11-01', 7, 'CANCELLED', 'Scheduling conflict', '2025-10-26 12:00:00');
