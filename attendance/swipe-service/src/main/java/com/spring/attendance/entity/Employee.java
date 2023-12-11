package com.spring.attendance.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "EMPID")
    private String empId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SWIPETYPE")
    private String swipeType;

    @Column(name = "TIMESTAMP")
    private LocalDateTime timeStamp;


    public Employee(int id, String empId, String name, String swipeType, LocalDateTime timeStamp) {
        this.id = id;
        this.empId = empId;
        this.name = name;
        this.swipeType = swipeType;
        this.timeStamp = timeStamp;
    }

    public Employee() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSwipeType() {
        return swipeType;
    }

    public void setSwipeType(String swipeType) {
        this.swipeType = swipeType;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}



