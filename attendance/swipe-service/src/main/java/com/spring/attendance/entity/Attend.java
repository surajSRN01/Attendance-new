package com.spring.attendance.entity;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "ATTEND")
public class Attend {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "EMPID")
    private String empId;

    @Column(name = "DATE")
    private LocalDateTime timeStamp;

    @Column(name = "TOTALHOURS")
    private long totHours;

    public Attend(int id, String empId, LocalDateTime timeStamp,long totHours) {
        this.id = id;
        this.empId = empId;
        this.timeStamp = timeStamp;
        this.totHours = totHours;
    }

    public Attend() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public long getTotHours() {
        return totHours;
    }

    public void setTotHours(long totHours) {
        this.totHours = totHours;
    }
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}



