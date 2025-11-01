package com.swe.project.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "T_DOCTOR")
public class Doctors {
    @Id
    @GeneratedValue()
    private Integer id;
    @Column(nullable = false)
    private String doctorName;

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Departments department;
    @Column(nullable = true)
    private int experience=0;
    @Column(nullable = true)
    private String doctorEmail;
    @Column(nullable = true)
    private String doctorPhone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }



    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }
}
