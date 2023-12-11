package com.spring.attendance.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class Attendance {

    private String empId;


    private LocalDateTime timeStamp;

    public Attendance(String empId, LocalDateTime timeStamp) {
        this.empId = empId;
        this.timeStamp = timeStamp;
    }

    public Attendance() {
    }


    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }


    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}



