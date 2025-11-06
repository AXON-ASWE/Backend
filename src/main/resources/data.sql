-- =====================================================
-- MOCK DATA FOR AXON ACTIVE PROJECT DATABASE
-- =====================================================
-- Thứ tự insert: Users -> Patients/Doctors -> Departments -> Symptoms -> Department_Symptom -> Doctor_Workshift -> Appointments
-- =====================================================

-- =====================================================
-- 1. INSERT USERS (T_USER)
-- =====================================================
-- Password: "password123" đã được hash bằng BCrypt
-- Có thể thay đổi password hash nếu cần

-- Admin Users
INSERT INTO t_user (user_id, email, password_hash, full_name, phone, gender, date_of_birth, role, status, created_at) VALUES
(1, 'admin@hospital.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Nguyễn Văn Admin', '0901234567', 'Male', '1980-01-15', 'ADMIN', 'Active', '2024-01-01'),
(2, 'admin2@hospital.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Trần Thị Quản Trị', '0901234568', 'Female', '1985-03-20', 'ADMIN', 'Active', '2024-01-01');

-- Doctor Users
INSERT INTO t_user (user_id, email, password_hash, full_name, phone, gender, date_of_birth, role, status, created_at) VALUES
(3, 'doctor.nguyen@hospital.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Bs. Nguyễn Văn Hùng', '0912345678', 'Male', '1975-06-10', 'DOCTOR', 'Active', '2024-01-02'),
(4, 'doctor.tran@hospital.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Bs. Trần Thị Mai', '0912345679', 'Female', '1980-08-25', 'DOCTOR', 'Active', '2024-01-02'),
(5, 'doctor.le@hospital.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Bs. Lê Minh Tuấn', '0912345680', 'Male', '1982-11-05', 'DOCTOR', 'Active', '2024-01-02'),
(6, 'doctor.pham@hospital.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Bs. Phạm Thị Lan', '0912345681', 'Female', '1978-04-15', 'DOCTOR', 'Active', '2024-01-02'),
(7, 'doctor.hoang@hospital.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Bs. Hoàng Văn Đức', '0912345682', 'Male', '1983-09-30', 'DOCTOR', 'Active', '2024-01-02'),
(8, 'doctor.vu@hospital.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Bs. Vũ Thị Hương', '0912345683', 'Female', '1981-07-12', 'DOCTOR', 'Active', '2024-01-02');

-- Patient Users
INSERT INTO t_user (user_id, email, password_hash, full_name, phone, gender, date_of_birth, role, status, created_at) VALUES
(9, 'patient1@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Nguyễn Thị Lan', '0923456789', 'Female', '1990-03-15', 'PATIENT', 'Active', '2024-02-01'),
(10, 'patient2@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Trần Văn Nam', '0923456790', 'Male', '1985-07-20', 'PATIENT', 'Active', '2024-02-02'),
(11, 'patient3@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Lê Thị Hoa', '0923456791', 'Female', '1992-11-08', 'PATIENT', 'Active', '2024-02-03'),
(12, 'patient4@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Phạm Văn Bình', '0923456792', 'Male', '1988-05-25', 'PATIENT', 'Active', '2024-02-04'),
(13, 'patient5@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Hoàng Thị Mai', '0923456793', 'Female', '1995-09-14', 'PATIENT', 'Active', '2024-02-05'),
(14, 'patient6@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Vũ Văn Toàn', '0923456794', 'Male', '1987-12-03', 'PATIENT', 'Active', '2024-02-06'),
(15, 'patient7@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Đặng Thị Thu', '0923456795', 'Female', '1993-02-28', 'PATIENT', 'Active', '2024-02-07'),
(16, 'patient8@gmail.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Ngô Văn Khánh', '0923456796', 'Male', '1991-06-17', 'PATIENT', 'Active', '2024-02-08');

-- Reset sequence for user_id
ALTER SEQUENCE t_user_user_id_seq RESTART WITH 17;

-- =====================================================
-- 2. INSERT DEPARTMENTS (T_DEPARTMENT)
-- =====================================================
INSERT INTO t_department (department_id, department_name, description, location) VALUES
(1, 'Khoa Tim Mạch', 'Chuyên về điều trị các bệnh về tim mạch, huyết áp, nhồi máu cơ tim', 'Tầng 3, Khu A'),
(2, 'Khoa Nội Tiết', 'Điều trị các bệnh về nội tiết, tiểu đường, tuyến giáp', 'Tầng 2, Khu A'),
(3, 'Khoa Tiêu Hóa', 'Chuyên khoa về dạ dày, ruột, gan mật', 'Tầng 4, Khu B'),
(4, 'Khoa Thần Kinh', 'Điều trị các bệnh về thần kinh, đau đầu, đột quỵ', 'Tầng 5, Khu A'),
(5, 'Khoa Da Liễu', 'Chuyên khoa về các bệnh da liễu, dị ứng da', 'Tầng 1, Khu C'),
(6, 'Khoa Tai Mũi Họng', 'Điều trị các bệnh về tai, mũi, họng', 'Tầng 2, Khu C');

-- Reset sequence for department_id
ALTER SEQUENCE t_department_department_id_seq RESTART WITH 7;

-- =====================================================
-- 3. INSERT SYMPTOMS (T_SYMPTOM)
-- =====================================================
INSERT INTO t_symptom (symptom_id, symptom_name, description, tag) VALUES
-- Tim Mạch
(1, 'Đau ngực', 'Cảm giác đau, tức ngực, khó thở', 'cardiac'),
(2, 'Tim đập nhanh', 'Nhịp tim tăng cao bất thường', 'cardiac'),
(3, 'Huyết áp cao', 'Tăng huyết áp liên tục', 'cardiac'),
-- Nội Tiết
(4, 'Khát nước nhiều', 'Cảm giác khát liên tục, tiểu nhiều', 'endocrine'),
(5, 'Tăng/giảm cân đột ngột', 'Thay đổi cân nặng không rõ nguyên nhân', 'endocrine'),
(6, 'Mệt mỏi kéo dài', 'Cảm thấy mệt mỏi, uể oải thường xuyên', 'endocrine'),
-- Tiêu Hóa
(7, 'Đau bụng', 'Đau vùng bụng, khó chịu', 'digestive'),
(8, 'Buồn nôn', 'Cảm giác buồn nôn, nôn mửa', 'digestive'),
(9, 'Tiêu chảy', 'Đi ngoài nhiều lần, phân lỏng', 'digestive'),
(10, 'Táo bón', 'Khó đi ngoài, phân cứng', 'digestive'),
-- Thần Kinh
(11, 'Đau đầu', 'Đau đầu dữ dội, kéo dài', 'neurological'),
(12, 'Chóng mặt', 'Cảm giác chóng mặt, mất thăng bằng', 'neurological'),
(13, 'Tê liệt', 'Tê, liệt tay chân', 'neurological'),
-- Da Liễu
(14, 'Ngứa da', 'Da bị ngứa, đỏ, khó chịu', 'dermatological'),
(15, 'Phát ban', 'Xuất hiện các vết ban đỏ trên da', 'dermatological'),
(16, 'Mụn', 'Da bị mụn, viêm', 'dermatological'),
-- Tai Mũi Họng
(17, 'Đau họng', 'Đau, khó nuốt', 'ent'),
(18, 'Nghẹt mũi', 'Khó thở qua mũi', 'ent'),
(19, 'Ù tai', 'Tai kêu, ù tai', 'ent');

-- Reset sequence for symptom_id
ALTER SEQUENCE t_symptom_symptom_id_seq RESTART WITH 20;

-- =====================================================
-- 4. INSERT DEPARTMENT-SYMPTOM RELATIONSHIPS (T_DEPARTMENT_SYMPTOM)
-- =====================================================
-- Tim Mạch
INSERT INTO t_department_symptom (department_id, symptom_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 12);

-- Nội Tiết
INSERT INTO t_department_symptom (department_id, symptom_id) VALUES
(2, 4), (2, 5), (2, 6);

-- Tiêu Hóa
INSERT INTO t_department_symptom (department_id, symptom_id) VALUES
(3, 7), (3, 8), (3, 9), (3, 10);

-- Thần Kinh
INSERT INTO t_department_symptom (department_id, symptom_id) VALUES
(4, 11), (4, 12), (4, 13);

-- Da Liễu
INSERT INTO t_department_symptom (department_id, symptom_id) VALUES
(5, 14), (5, 15), (5, 16);

-- Tai Mũi Họng
INSERT INTO t_department_symptom (department_id, symptom_id) VALUES
(6, 17), (6, 18), (6, 19);

-- =====================================================
-- 5. INSERT PATIENTS (T_PATIENT)
-- =====================================================
INSERT INTO t_patient (patient_id, user_id, address, emergency_contact, insurance_number) VALUES
(1, 9, '123 Lê Lợi, Quận 1, TP.HCM', '0987654321', 'BH123456789'),
(2, 10, '456 Trần Hưng Đạo, Quận 5, TP.HCM', '0987654322', 'BH123456790'),
(3, 11, '789 Nguyễn Trãi, Quận 1, TP.HCM', '0987654323', 'BH123456791'),
(4, 12, '321 Hai Bà Trưng, Quận 3, TP.HCM', '0987654324', 'BH123456792'),
(5, 13, '654 Cách Mạng Tháng 8, Quận 10, TP.HCM', '0987654325', 'BH123456793'),
(6, 14, '987 Võ Văn Tần, Quận 3, TP.HCM', '0987654326', 'BH123456794'),
(7, 15, '147 Pasteur, Quận 1, TP.HCM', '0987654327', 'BH123456795'),
(8, 16, '258 Điện Biên Phủ, Quận Bình Thạnh, TP.HCM', '0987654328', 'BH123456796');

-- Reset sequence for patient_id
ALTER SEQUENCE t_patient_patient_id_seq RESTART WITH 9;

-- =====================================================
-- 6. INSERT DOCTORS (T_DOCTOR)
-- =====================================================
INSERT INTO t_doctor (doctor_id, user_id, specialization, experience_years, qualifications, department_id) VALUES
(1, 3, 'Tim Mạch Học', 15, 'Bác sĩ chuyên khoa II Tim Mạch, Thạc sĩ Y học', 1),
(2, 4, 'Nội Tiết', 12, 'Bác sĩ chuyên khoa I Nội Tiết, Tiến sĩ Y học', 2),
(3, 5, 'Tiêu Hóa', 10, 'Bác sĩ chuyên khoa I Tiêu Hóa', 3),
(4, 6, 'Thần Kinh', 18, 'Bác sĩ chuyên khoa II Thần Kinh, Giáo sư Y học', 4),
(5, 7, 'Da Liễu', 8, 'Bác sĩ chuyên khoa I Da Liễu', 5),
(6, 8, 'Tai Mũi Họng', 14, 'Bác sĩ chuyên khoa II Tai Mũi Họng, Thạc sĩ Y học', 6);

-- Reset sequence for doctor_id
ALTER SEQUENCE t_doctor_doctor_id_seq RESTART WITH 7;

-- =====================================================
-- 7. INSERT DOCTOR WORKSHIFTS (T_DOCTOR_WORKSHIFT)
-- =====================================================
-- Bác sĩ 1 (Tim Mạch): Thứ 2, 4, 6
INSERT INTO t_doctor_workshift (doctor_id, day_of_week, start_time, end_time) VALUES
(1, 'MONDAY', '08:00:00', '12:00:00'),
(1, 'MONDAY', '13:00:00', '17:00:00'),
(1, 'WEDNESDAY', '08:00:00', '12:00:00'),
(1, 'WEDNESDAY', '13:00:00', '17:00:00'),
(1, 'FRIDAY', '08:00:00', '12:00:00'),
(1, 'FRIDAY', '13:00:00', '17:00:00');

-- Bác sĩ 2 (Nội Tiết): Thứ 3, 5, 7
INSERT INTO t_doctor_workshift (doctor_id, day_of_week, start_time, end_time) VALUES
(2, 'TUESDAY', '08:00:00', '12:00:00'),
(2, 'TUESDAY', '13:00:00', '17:00:00'),
(2, 'THURSDAY', '08:00:00', '12:00:00'),
(2, 'THURSDAY', '13:00:00', '17:00:00'),
(2, 'SATURDAY', '08:00:00', '12:00:00');

-- Bác sĩ 3 (Tiêu Hóa): Thứ 2, 3, 4
INSERT INTO t_doctor_workshift (doctor_id, day_of_week, start_time, end_time) VALUES
(3, 'MONDAY', '08:00:00', '12:00:00'),
(3, 'TUESDAY', '13:00:00', '17:00:00'),
(3, 'WEDNESDAY', '08:00:00', '12:00:00'),
(3, 'THURSDAY', '13:00:00', '17:00:00');

-- Bác sĩ 4 (Thần Kinh): Thứ 4, 5, 6
INSERT INTO t_doctor_workshift (doctor_id, day_of_week, start_time, end_time) VALUES
(4, 'WEDNESDAY', '08:00:00', '12:00:00'),
(4, 'WEDNESDAY', '13:00:00', '17:00:00'),
(4, 'THURSDAY', '08:00:00', '12:00:00'),
(4, 'FRIDAY', '13:00:00', '17:00:00'),
(4, 'SATURDAY', '08:00:00', '12:00:00');

-- Bác sĩ 5 (Da Liễu): Thứ 2, 4, 6, 7
INSERT INTO t_doctor_workshift (doctor_id, day_of_week, start_time, end_time) VALUES
(5, 'MONDAY', '13:00:00', '17:00:00'),
(5, 'WEDNESDAY', '13:00:00', '17:00:00'),
(5, 'FRIDAY', '08:00:00', '12:00:00'),
(5, 'FRIDAY', '13:00:00', '17:00:00'),
(5, 'SATURDAY', '08:00:00', '12:00:00');

-- Bác sĩ 6 (Tai Mũi Họng): Thứ 3, 5, 7
INSERT INTO t_doctor_workshift (doctor_id, day_of_week, start_time, end_time) VALUES
(6, 'TUESDAY', '08:00:00', '12:00:00'),
(6, 'TUESDAY', '13:00:00', '17:00:00'),
(6, 'THURSDAY', '08:00:00', '12:00:00'),
(6, 'THURSDAY', '13:00:00', '17:00:00'),
(6, 'SATURDAY', '13:00:00', '17:00:00');

-- =====================================================
-- 8. INSERT APPOINTMENTS (T_APPOINTMENT)
-- =====================================================
-- Các cuộc hẹn trong tương lai và quá khứ
INSERT INTO t_appointment (appointment_id, patient_id, doctor_id, appointment_date, time_slot, status, notes, created_at) VALUES
-- Appointments đã hoàn thành
(1, 1, 1, '2024-10-15', 1, 'Completed', 'Khám định kỳ tim mạch. Bệnh nhân đã được kiểm tra ECG.', '2024-10-10 09:30:00'),
(2, 2, 2, '2024-10-18', 2, 'Completed', 'Tái khám tiểu đường. Kết quả đường huyết ổn định.', '2024-10-12 10:15:00'),
(3, 3, 3, '2024-10-20', 1, 'Completed', 'Khám đau bụng. Chẩn đoán viêm dạ dày nhẹ.', '2024-10-15 14:20:00'),
(4, 4, 4, '2024-10-22', 3, 'Completed', 'Khám đau đầu mạn tính. Đã chụp CT scan não.', '2024-10-17 11:00:00'),

-- Appointments đã xác nhận (sắp tới)
(5, 5, 5, '2024-11-08', 2, 'Confirmed', 'Khám dị ứng da, mẩn đỏ', '2024-11-01 08:45:00'),
(6, 6, 6, '2024-11-09', 1, 'Confirmed', 'Khám viêm họng, ho kéo dài', '2024-11-02 09:30:00'),
(7, 7, 1, '2024-11-10', 3, 'Confirmed', 'Khám kiểm tra sức khỏe tim mạch định kỳ', '2024-11-03 10:00:00'),
(8, 8, 2, '2024-11-11', 1, 'Confirmed', 'Tái khám tuyến giáp', '2024-11-04 11:30:00'),

-- Appointments đang chờ xác nhận
(9, 1, 3, '2024-11-12', 2, 'Pending', 'Khám đau dạ dày', '2024-11-05 14:00:00'),
(10, 2, 4, '2024-11-13', 3, 'Pending', 'Khám chóng mặt thường xuyên', '2024-11-05 15:30:00'),

-- Appointments bị hủy
(11, 3, 5, '2024-10-25', 1, 'Cancelled', 'Bệnh nhân hủy lịch hẹn', '2024-10-20 16:00:00'),
(12, 4, 6, '2024-10-28', 2, 'Cancelled', 'Bác sĩ bận công việc đột xuất', '2024-10-23 09:00:00'),

-- Appointments trong tương lai xa
(13, 5, 1, '2024-11-15', 1, 'Confirmed', 'Kiểm tra huyết áp định kỳ', '2024-11-06 10:00:00'),
(14, 6, 2, '2024-11-16', 2, 'Pending', 'Khám sức khỏe tổng quát', '2024-11-06 11:00:00'),
(15, 7, 3, '2024-11-17', 3, 'Confirmed', 'Tái khám sau điều trị dạ dày', '2024-11-06 13:00:00'),
(16, 8, 4, '2024-11-18', 1, 'Pending', 'Khám đau nửa đầu migraine', '2024-11-06 14:00:00');

-- Reset sequence for appointment_id
ALTER SEQUENCE t_appointment_appointment_id_seq RESTART WITH 17;

-- =====================================================
-- END OF MOCK DATA
-- =====================================================
