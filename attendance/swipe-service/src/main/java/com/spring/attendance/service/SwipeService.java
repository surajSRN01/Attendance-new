package com.spring.attendance.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.kafka.core.KafkaTemplate;

import com.spring.attendance.dao.SwipeAttendRepository;
import com.spring.attendance.entity.Attend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.attendance.dao.SwipeRepository;
import com.spring.attendance.entity.Employee;

@Service
public class SwipeService {
	
	@Autowired
	private SwipeRepository swipeRepository;

	@Autowired
	private SwipeAttendRepository swipeAttendRepository;

	public void handleSwipeIn(Employee swipEmployee) {
//       swipEmployee. setTimeStamp(LocalDateTime.now());
		swipEmployee.setSwipeType("in");
        swipeRepository.save(swipEmployee);
		kafkaTemplate.send("swipe-events", swipEmployee.getEmpId(), swipEmployee);
		
	}
	
	public void handleSwipeOut(Employee swipEmployee) {
//	       swipEmployee. setTimeStamp(LocalDateTime.now());
			swipEmployee.setSwipeType("out");
	       swipeRepository.save(swipEmployee);
			
	}
	
	public Duration calculateTotalHoursInOffice(Employee employee, LocalDateTime date) {
        // Retrieve swipes for the employee on the given date
		List<Employee> employeeSwipes = swipeRepository.findByEmpIdAndTimeStampBetween(
		        employee.getEmpId(),
		        date,
		        date.plusDays(1) // Calculate end of the day
		);

        // Sort swipes by timestamp in ascending order
        employeeSwipes.sort((swipe1, swipe2) -> swipe1.getTimeStamp().compareTo(swipe2.getTimeStamp()));

        if (employeeSwipes.isEmpty()) {
            // No swipes for the given date
            return Duration.ZERO;
        }

        // Get the first swipe in and last swipe out
        LocalDateTime firstSwipeIn = employeeSwipes.get(0).getTimeStamp();
        LocalDateTime lastSwipeOut = employeeSwipes.get(employeeSwipes.size() - 1).getTimeStamp();

        // Calculate the duration between the first swipe in and last swipe out
		Duration totHour = Duration.between(firstSwipeIn, lastSwipeOut);
		Attend attend = new Attend();
		attend.setEmpId(employee.getEmpId());
		attend.setTimeStamp(date);
		attend.setTotHours(totHour.toHours());
		swipeAttendRepository.save(attend);
        return totHour;
    }

//	public int calculateTotalHoursInOffice(Employee employee, LocalDateTime date) {
//		// Retrieve 'IN' and 'OUT' swipes for the employee on the given date using the custom query
//		List<Employee> employeeSwipes = swipeRepository.findByEmpIdAndTimeStampBetween(
//				employee.getEmpId(),
//				date,
//				date.plusDays(1) // No need to subtract seconds for the end time
//		);
//
//		if (employeeSwipes.isEmpty()) {
//			return 0; // No swipes for the given date
//		}
//
//		// Filter only 'IN' and 'OUT' swipes
////		List<Employee> filteredSwipes = employeeSwipes.stream()
////				.filter(swipe -> "in".equals(swipe.getSwipeType()) || "out".equals(swipe.getSwipeType()))
////				.toList();
////
////		if (filteredSwipes.isEmpty()) {
////			return Duration.ZERO; // No 'IN' or 'OUT' swipes for the given date
////		}
//
//		// Sort swipes by timestamp in ascending order
//		int sum = 0;
//		employeeSwipes.sort((swipe1, swipe2) -> swipe1.getTimeStamp().compareTo(swipe2.getTimeStamp()));
//		for (Employee e:employeeSwipes) {
//			int indexOfFirstIN = e.indexOf(e.stream().filter(swipe -> "in".equals(swipe.getSwipeType())).findFirst().orElse(null));
//
//			// Find the last 'OUT' swipe
//			int indexOfLastOUT = employeeSwipes.lastIndexOf(employeeSwipes.stream().filter(swipe -> "out".equals(swipe.getSwipeType())).findFirst().orElse(null));
//			if (indexOfFirstIN == -1 || indexOfLastOUT == -1 || indexOfFirstIN > indexOfLastOUT) {
//				return 0; // Invalid swipes, either missing 'IN' or 'OUT'
//			}
//
//			// Get the first 'IN' swipe and last 'OUT' swipe
//			LocalDateTime firstSwipeIn = employeeSwipes.get(indexOfFirstIN).getTimeStamp();
//			LocalDateTime lastSwipeOut = employeeSwipes.get(indexOfLastOUT).getTimeStamp();
//
//			sum = sum+Integer.parseInt(String.valueOf(Duration.between(firstSwipeIn, lastSwipeOut)));
//
//		}
//		// Find the first 'IN' swipe
////		int indexOfFirstIN = employeeSwipes.indexOf(employeeSwipes.stream().filter(swipe -> "in".equals(swipe.getSwipeType())).findFirst().orElse(null));
////
////		// Find the last 'OUT' swipe
////		int indexOfLastOUT = employeeSwipes.lastIndexOf(employeeSwipes.stream().filter(swipe -> "out".equals(swipe.getSwipeType())).findFirst().orElse(null));
////
////		if (indexOfFirstIN == -1 || indexOfLastOUT == -1 || indexOfFirstIN > indexOfLastOUT) {
////			return Duration.ZERO; // Invalid swipes, either missing 'IN' or 'OUT'
////		}
////
////		// Get the first 'IN' swipe and last 'OUT' swipe
////		LocalDateTime firstSwipeIn = employeeSwipes.get(indexOfFirstIN).getTimeStamp();
////		LocalDateTime lastSwipeOut = employeeSwipes.get(indexOfLastOUT).getTimeStamp();
//
//		// Calculate the duration between the first 'IN' swipe and last 'OUT' swipe
//		return sum;
//	}

}
