package com.spring.attendance.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.attendance.entity.Employee;
import org.springframework.data.repository.query.Param;

public interface SwipeRepository extends JpaRepository<Employee, Integer>{
	@Query("SELECT e FROM Employee e WHERE e.empId = :empId AND e.timeStamp >= :startTime AND e.timeStamp < :endTime ORDER BY e.timeStamp ASC")
	List<Employee> findByEmpIdAndTimeStampBetween(String empId, LocalDateTime startTime, LocalDateTime endTime);

//	@Query("SELECT e FROM Employee e WHERE e.empId = :empId AND e.timeStamp >= :startTime AND e.timeStamp < :endTime AND e.swipeType IN ('IN', 'OUT') ORDER BY e.timeStamp ASC")
//	List<Employee> findByEmpIdAndTimeStampBetween(@Param("empId") String empId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
