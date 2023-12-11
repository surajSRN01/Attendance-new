package com.spring.attendance.rest;

import com.spring.attendance.entity.Attendance;
import com.spring.attendance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @GetMapping(value = "/attendance/{empId}/{date}")
    public ResponseEntity getAttendance(@PathVariable String empId, @PathVariable String date){
        LocalDateTime localDate = LocalDateTime.parse(date + "T00:00:00");

        Attendance employee = new Attendance(); // You might need to retrieve the employee from the database based on the empId
        employee.setEmpId(empId);
        String attendance = attendanceService.getAttendance(employee);

        return ResponseEntity.ok("The attendance on "+date+" is : " +attendance);

    }

}
