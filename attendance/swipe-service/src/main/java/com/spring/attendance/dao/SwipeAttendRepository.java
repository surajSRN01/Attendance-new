package com.spring.attendance.dao;

import com.spring.attendance.entity.Attend;
import com.spring.attendance.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SwipeAttendRepository extends JpaRepository<Attend, Integer> {
}
