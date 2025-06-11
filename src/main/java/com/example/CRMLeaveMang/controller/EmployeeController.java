package com.example.CRMLeaveMang.controller;

import java.util.List;

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
import com.example.CRMLeaveMang.model.LeaveRequest;

@Controller
@RequestMapping("/api/employee")
public class EmployeeController {

	
    @Autowired
    private LeaveService leaveService;

    // ✅ 1. Apply for Leave
    @PostMapping("/apply-leave")
    public ResponseEntity<String> applyLeave(@RequestBody LeaveRequestDto dto) {
        String result = leaveService.applyLeave(dto);
        return ResponseEntity.ok(result);
    }

    // ✅ 2. View All Leave Requests of a Particular Employee
    @GetMapping("/leaves/{empId}")
    public ResponseEntity<List<LeaveRequest>> getAllLeaves(@PathVariable String empId) {
        List<LeaveRequest> leaves = leaveService.getLeavesForEmployee(empId);
        return ResponseEntity.ok(leaves);
    }
}
