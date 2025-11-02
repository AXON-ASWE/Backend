-- ============================================
-- MOCK DATA FOR AXON PROJECT DATABASE
-- ============================================

-- Insert Users (T_USER)
INSERT INTO T_USER (id, username, password) VALUES 
(1, 'admin', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6'), -- password: admin123
(2, 'john.doe', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6'), -- password: admin123
(3, 'jane.smith', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6'),
(4, 'bob.wilson', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6'),
(5, 'alice.brown', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6'),
(6, 'charlie.davis', '$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6');

-- Insert Symptoms (T_SYMPTOM)
INSERT INTO T_SYMPTOM (symptom_id, symptom_name, description, tag) VALUES 
(1, 'Fever', 'High body temperature', 'common'),
(2, 'Headache', 'Pain in head region', 'common'),
(3, 'Chest Pain', 'Discomfort in chest area', 'urgent'),
(4, 'Shortness of Breath', 'Difficulty breathing', 'urgent'),
(5, 'Cough', 'Persistent coughing', 'common'),
(6, 'Fatigue', 'Extreme tiredness', 'common'),
(7, 'Nausea', 'Feeling of sickness', 'common'),
(8, 'Dizziness', 'Feeling lightheaded', 'common'),
(9, 'Abdominal Pain', 'Pain in stomach area', 'moderate'),
(10, 'Back Pain', 'Pain in back region', 'moderate'),
(11, 'Joint Pain', 'Pain in joints', 'moderate'),
(12, 'Skin Rash', 'Unusual skin condition', 'moderate'),
(13, 'Anxiety', 'Feeling of worry or unease', 'mental'),
(14, 'Depression', 'Persistent sadness', 'mental'),
(15, 'Toothache', 'Pain in teeth or gums', 'dental'),
(16, 'Blurred Vision', 'Unclear vision', 'eye'),
(17, 'Ear Pain', 'Pain in ear', 'ent'),
(18, 'Frequent Urination', 'Need to urinate often', 'urological'),
(19, 'Irregular Periods', 'Abnormal menstrual cycle', 'gynecological'),
(20, 'Memory Loss', 'Difficulty remembering', 'neurological');

-- Insert Departments (T_DEPARTMENT)
INSERT INTO T_DEPARTMENT (id, department_name, description, location) VALUES 
(1, 'Cardiology', 'Heart and cardiovascular system care', 'Building A, Floor 3'),
(2, 'Neurology', 'Brain and nervous system disorders', 'Building A, Floor 4'),
(3, 'Orthopedics', 'Bone, joint, and muscle treatment', 'Building B, Floor 2'),
(4, 'Pediatrics', 'Child healthcare', 'Building C, Floor 1'),
(5, 'Dermatology', 'Skin conditions and treatment', 'Building B, Floor 3'),
(6, 'Psychiatry', 'Mental health care', 'Building C, Floor 2'),
(7, 'General Medicine', 'General health consultation', 'Building A, Floor 1'),
(8, 'Emergency', 'Emergency medical care', 'Building A, Ground Floor'),
(9, 'Dentistry', 'Dental care and oral health', 'Building B, Floor 1'),
(10, 'Ophthalmology', 'Eye care and vision', 'Building C, Floor 3'),
(11, 'ENT', 'Ear, Nose, and Throat care', 'Building B, Floor 4'),
(12, 'Urology', 'Urinary system and male reproductive organs', 'Building A, Floor 5'),
(13, 'Gynecology', 'Women health and reproductive system', 'Building C, Floor 4'),
(14, 'Gastroenterology', 'Digestive system disorders', 'Building A, Floor 2');

-- Link Departments with Symptoms (T_DEPARTMENT_SYMPTOM)
-- Cardiology symptoms
INSERT INTO T_DEPARTMENT_SYMPTOM (department_id, symptom_id) VALUES 
(1, 3), (1, 4), (1, 6), (1, 8);

-- Neurology symptoms
INSERT INTO T_DEPARTMENT_SYMPTOM (department_id, symptom_id) VALUES 
(2, 2), (2, 8), (2, 20);

-- Orthopedics symptoms
INSERT INTO T_DEPARTMENT_SYMPTOM (department_id, symptom_id) VALUES 
(3, 10), (3, 11);

-- Pediatrics symptoms (common children symptoms)
INSERT INTO T_DEPARTMENT_SYMPTOM (department_id, symptom_id) VALUES 
(4, 1), (4, 5), (4, 7);

-- Dermatology symptoms
INSERT INTO T_DEPARTMENT_SYMPTOM (department_id, symptom_id) VALUES 
(5, 12);

-- Psychiatry symptoms
INSERT INTO T_DEPARTMENT_SYMPTOM (department_id, symptom_id) VALUES 
(6, 13), (6, 14);

-- General Medicine symptoms
INSERT INTO T_DEPARTMENT_SYMPTOM (department_id, symptom_id) VALUES 
(7, 1), (7, 2), (7, 5), (7, 6), (7, 7);

-- Emergency symptoms
INSERT INTO T_DEPARTMENT_SYMPTOM (department_id, symptom_id) VALUES 
(8, 3), (8, 4), (8, 8);

-- Dentistry symptoms
INSERT INTO T_DEPARTMENT_SYMPTOM (department_id, symptom_id) VALUES 
(9, 15);

-- Ophthalmology symptoms
INSERT INTO T_DEPARTMENT_SYMPTOM (department_id, symptom_id) VALUES 
(10, 16);

-- ENT symptoms
INSERT INTO T_DEPARTMENT_SYMPTOM (department_id, symptom_id) VALUES 
(11, 17);

-- Urology symptoms
INSERT INTO T_DEPARTMENT_SYMPTOM (department_id, symptom_id) VALUES 
(12, 18);

-- Gynecology symptoms
INSERT INTO T_DEPARTMENT_SYMPTOM (department_id, symptom_id) VALUES 
(13, 19);

-- Gastroenterology symptoms
INSERT INTO T_DEPARTMENT_SYMPTOM (department_id, symptom_id) VALUES 
(14, 7), (14, 9);

-- Insert Doctors (T_DOCTOR)
INSERT INTO T_DOCTOR (id, doctor_name, department_id, experience, doctor_email, doctor_phone) VALUES 
-- Cardiology
(1, 'Dr. Michael Chen', 1, 15, 'michael.chen@hospital.com', '555-0101'),
(2, 'Dr. Sarah Johnson', 1, 12, 'sarah.johnson@hospital.com', '555-0102'),

-- Neurology
(3, 'Dr. Robert Williams', 2, 20, 'robert.williams@hospital.com', '555-0103'),
(4, 'Dr. Emily Davis', 2, 8, 'emily.davis@hospital.com', '555-0104'),

-- Orthopedics
(5, 'Dr. James Martinez', 3, 18, 'james.martinez@hospital.com', '555-0105'),
(6, 'Dr. Lisa Anderson', 3, 10, 'lisa.anderson@hospital.com', '555-0106'),

-- Pediatrics
(7, 'Dr. David Thompson', 4, 14, 'david.thompson@hospital.com', '555-0107'),
(8, 'Dr. Jennifer White', 4, 9, 'jennifer.white@hospital.com', '555-0108'),

-- Dermatology
(9, 'Dr. Christopher Lee', 5, 11, 'christopher.lee@hospital.com', '555-0109'),
(10, 'Dr. Amanda Garcia', 5, 7, 'amanda.garcia@hospital.com', '555-0110'),

-- Psychiatry
(11, 'Dr. Daniel Rodriguez', 6, 16, 'daniel.rodriguez@hospital.com', '555-0111'),
(12, 'Dr. Patricia Martinez', 6, 13, 'patricia.martinez@hospital.com', '555-0112'),

-- General Medicine
(13, 'Dr. Kevin Brown', 7, 10, 'kevin.brown@hospital.com', '555-0113'),
(14, 'Dr. Michelle Wilson', 7, 8, 'michelle.wilson@hospital.com', '555-0114'),

-- Emergency
(15, 'Dr. Steven Taylor', 8, 12, 'steven.taylor@hospital.com', '555-0115'),
(16, 'Dr. Laura Thomas', 8, 9, 'laura.thomas@hospital.com', '555-0116'),

-- Dentistry
(17, 'Dr. Brian Moore', 9, 15, 'brian.moore@hospital.com', '555-0117'),
(18, 'Dr. Karen Jackson', 9, 11, 'karen.jackson@hospital.com', '555-0118'),

-- Ophthalmology
(19, 'Dr. Richard Martin', 10, 17, 'richard.martin@hospital.com', '555-0119'),
(20, 'Dr. Nancy Lee', 10, 10, 'nancy.lee@hospital.com', '555-0120'),

-- ENT
(21, 'Dr. Paul Harris', 11, 14, 'paul.harris@hospital.com', '555-0121'),
(22, 'Dr. Sandra Clark', 11, 9, 'sandra.clark@hospital.com', '555-0122'),

-- Urology
(23, 'Dr. Mark Lewis', 12, 19, 'mark.lewis@hospital.com', '555-0123'),
(24, 'Dr. Barbara Walker', 12, 12, 'barbara.walker@hospital.com', '555-0124'),

-- Gynecology
(25, 'Dr. Thomas Hall', 13, 16, 'thomas.hall@hospital.com', '555-0125'),
(26, 'Dr. Elizabeth Allen', 13, 11, 'elizabeth.allen@hospital.com', '555-0126'),

-- Gastroenterology
(27, 'Dr. Joseph Young', 14, 13, 'joseph.young@hospital.com', '555-0127'),
(28, 'Dr. Susan King', 14, 10, 'susan.king@hospital.com', '555-0128');

-- Insert Patients (T_PATIENT)
INSERT INTO T_PATIENT (id, user_id, address, medical_history, emergency_contact, insurance_number) VALUES 
(1, 2, '123 Main St, New York, NY 10001', 'Hypertension, Type 2 Diabetes', '555-1001', 'INS-001-2024'),
(2, 3, '456 Oak Ave, Los Angeles, CA 90001', 'Asthma', '555-1002', 'INS-002-2024'),
(3, 4, '789 Pine Rd, Chicago, IL 60601', 'None', '555-1003', 'INS-003-2024'),
(4, 5, '321 Elm St, Houston, TX 77001', 'Allergies (Peanuts)', '555-1004', 'INS-004-2024'),
(5, 6, '654 Maple Dr, Phoenix, AZ 85001', 'Previous heart surgery (2020)', '555-1005', 'INS-005-2024');

-- Insert Appointments (T_APPOINTMENT)
INSERT INTO T_APPOINTMENT (appointment_id, patient_id, doctor_id, appointment_date, start_time, end_time, status, notes, created_at) VALUES 
-- Upcoming appointments
(1, 1, 1, '2025-11-05', '09:00:00', '09:30:00', 'SCHEDULED', 'Regular cardiology checkup', '2025-10-28 10:00:00'),
(2, 2, 7, '2025-11-06', '10:00:00', '10:30:00', 'SCHEDULED', 'Asthma follow-up', '2025-10-29 14:30:00'),
(3, 3, 13, '2025-11-07', '14:00:00', '14:30:00', 'SCHEDULED', 'General health consultation', '2025-10-30 09:15:00'),
(4, 4, 5, '2025-11-08', '11:00:00', '11:30:00', 'SCHEDULED', 'Joint pain examination', '2025-10-31 16:20:00'),
(5, 5, 1, '2025-11-10', '15:00:00', '15:45:00', 'SCHEDULED', 'Post-surgery follow-up', '2025-11-01 08:00:00'),

-- Past appointments
(6, 1, 13, '2025-10-20', '09:00:00', '09:30:00', 'COMPLETED', 'Annual checkup - all clear', '2025-10-15 11:00:00'),
(7, 2, 3, '2025-10-22', '10:30:00', '11:00:00', 'COMPLETED', 'Headache consultation', '2025-10-18 13:30:00'),
(8, 3, 14, '2025-10-25', '13:00:00', '13:30:00', 'COMPLETED', 'Fever and cold', '2025-10-20 10:00:00'),
(9, 4, 9, '2025-10-28', '14:30:00', '15:00:00', 'COMPLETED', 'Skin rash treatment', '2025-10-23 15:45:00'),
(10, 5, 2, '2025-10-30', '11:00:00', '11:45:00', 'COMPLETED', 'Cardiac monitoring', '2025-10-25 09:30:00'),

-- Cancelled appointment
(11, 2, 4, '2025-10-31', '16:00:00', '16:30:00', 'CANCELLED', 'Patient requested cancellation', '2025-10-26 12:00:00');
