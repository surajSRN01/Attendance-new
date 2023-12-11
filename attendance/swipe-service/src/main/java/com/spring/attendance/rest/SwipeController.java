package com.spring.attendance.rest;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.attendance.entity.Employee;
import com.spring.attendance.service.SwipeService;

@RestController
public class SwipeController {
	
	@Autowired
	private SwipeService swipe;
	
	@PostMapping(value="/in")
	public ResponseEntity swipeIn(@RequestBody Employee swipEmployee) {
		swipe.handleSwipeIn(swipEmployee);
		return ResponseEntity.ok().build();
	     
	}
	
	@PostMapping(value="/out")
	public ResponseEntity swipeOut(@RequestBody Employee swipEmployee) {
		swipe.handleSwipeOut(swipEmployee);
		return ResponseEntity.ok().build();
	     
	}
	
	 @GetMapping("/total-hours/{empId}/{date}")
	    public ResponseEntity<String> getTotalHoursInOffice(
	            @PathVariable String empId,
	            @PathVariable String date) {

	        // Assuming date is in the format "yyyy-MM-dd"
	        LocalDateTime localDate = LocalDateTime.parse(date + "T00:00:00");

	        Employee employee = new Employee(); // You might need to retrieve the employee from the database based on the empId
			employee.setEmpId(empId);
	        Duration totalHours = swipe.calculateTotalHoursInOffice(employee, localDate);

	        return ResponseEntity.ok("Total hours in office: " + totalHours.toHours() + " hours");
//		 int totalHours = swipe.calculateTotalHoursInOffice(employee, localDate);
//
//		 return ResponseEntity.ok("Total hours in office: " + totalHours + " hours");
	    }

}
