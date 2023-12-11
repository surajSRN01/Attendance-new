package com.spring.attendance.dao;

import com.spring.attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance, Integer> {

    List<Attendance> findByEmpId(String empId);
}
