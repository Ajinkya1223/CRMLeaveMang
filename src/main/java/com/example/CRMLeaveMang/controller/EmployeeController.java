package com.example.CRMLeaveMang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.CRMLeaveMang.dto.LeaveRequestDto;
import com.example.CRMLeaveMang.service.LeaveService;
import com.example.CRMLeaveMang.model.Employee;
import com.example.CRMLeaveMang.model.LeaveRequest;
import com.example.CRMLeaveMang.repository.EmployeeRepository;

@Controller
@RequestMapping("/api/employee")
public class EmployeeController {

	
    @Autowired
    private LeaveService leaveService;
    
    @Autowired
    private EmployeeRepository employeeRepository;

    //   Apply for Leave
    @PostMapping("/apply-leave")
    public ResponseEntity<String> applyLeave(@RequestBody LeaveRequestDto dto) {
        String result = leaveService.applyLeave(dto);
        return ResponseEntity.ok(result);
    }

    //   View All Leave Requests of a Particular Employee
    @GetMapping("/leaves/{empId}")
    public ResponseEntity<List<LeaveRequest>> getAllLeaves(@PathVariable String empId) {
        List<LeaveRequest> leaves = leaveService.getLeavesForEmployee(empId);
        return ResponseEntity.ok(leaves);
    }
    
    // View Leave Balance (total, taken, balance)
    @GetMapping("/leave-balance/{empId}")
    public ResponseEntity<?> getLeaveBalance(@PathVariable String empId) {
        Optional<Employee> empOpt = employeeRepository.findByEmpId(empId);
        if (!empOpt.isPresent()) {
            return ResponseEntity.status(404).body("Employee Not Found");
        }
        
        Employee emp = empOpt.get();
        Map<String, Object> balance = new HashMap<>();
        balance.put("totalLeaves", emp.getTotalLeaves());
        balance.put("takenLeaves", emp.getTakenLeaves());
        balance.put("balanceLeaves", emp.getBalanceleave());

        return ResponseEntity.ok(balance);
    }
    
    
    // View Profile (includes personal + leave info)
    
    @GetMapping("/profile/{empId}")
    public ResponseEntity<?> getEmployeeProfile(@PathVariable String empId) {
        Optional<Employee> empOpt = employeeRepository.findByEmpId(empId);
        if (!empOpt.isPresent()) {
            return ResponseEntity.status(404).body("Employee Not Found");
        }

        Employee emp = empOpt.get();
        Map<String, Object> profile = new HashMap<>();
        profile.put("empId", emp.getEmpId());
        profile.put("name", emp.getName());
        profile.put("email", emp.getEmail());
        profile.put("totalLeaves", emp.getTotalLeaves());
        profile.put("takenLeaves", emp.getTakenLeaves());
        profile.put("balanceLeaves", emp.getBalanceleave());
        profile.put("halfDayCount", emp.getHalfDayCount());

        return ResponseEntity.ok(profile);
    }
}
    
    

