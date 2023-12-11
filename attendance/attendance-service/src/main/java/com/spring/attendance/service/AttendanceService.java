package com.spring.attendance.service;

import com.spring.attendance.dao.AttendanceRepo;
import com.spring.attendance.entity.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepo attendanceRepo;

    public String getAttendance(Attendance employee){
        List<Attendance> employeeAtt = attendanceRepo.findByEmpId(employee.getEmpId());
//        int totHours = employeeAtt.get(employeeAtt.indexOf(3));
        return "Hi";
    }
}
