//package com.example.CRMLeaveMang.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.example.CRMLeaveMang.model.LeaveRequest;
//import com.example.CRMLeaveMang.service.LeaveService;
//
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/hr")
//public class HrController {
//
//	@Autowired
//    private LeaveService leaveService;
//
//    // 1. Get all leave requests
//    @GetMapping("/leave-requests")
//    public ResponseEntity<List<LeaveRequest>> getAllLeaveRequests() {
//        return ResponseEntity.ok(leaveService.getAllLeaveRequests());
//    }
//
//    // 2. Approve or Reject Leave by Leave ID
//    @PutMapping("/update-status/{leaveId}")
//    public ResponseEntity<String> updateLeaveStatus(@PathVariable Long leaveId, @RequestParam String status) {
//        return ResponseEntity.ok(leaveService.updateLeaveStatus(leaveId, status));
//    }
//
//    // 3. View leave history for specific employee
//    @GetMapping("/leave-requests/{empId}")
//    public ResponseEntity<List<LeaveRequest>> getLeaveHistory(@PathVariable String empId) {
//        return ResponseEntity.ok(leaveService.getLeavesForEmployee(empId));
//    }
//}


package com.example.CRMLeaveMang.controller;

import java.util.List;

import com.example.CRMLeaveMang.model.LeaveRequest;
import com.example.CRMLeaveMang.service.LeaveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hr")
public class HrController {

    @Autowired
    private LeaveService leaveService;

    // Get all leave requests
    @GetMapping("/leave-requests")
    public ResponseEntity<List<LeaveRequest>> getAllLeaveRequests() {
        return ResponseEntity.ok(leaveService.getAllLeaveRequests());
    }

    //  Approve or Reject Leave by Leave ID
    @PutMapping("/update-status/{leaveId}")
    public ResponseEntity<String> updateLeaveStatus(
            @PathVariable Long leaveId,
            @RequestParam String status) {

        String result = leaveService.updateLeaveStatus(leaveId, status);

        if (result.equals("Leave request not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else if (result.equals("Employee email is missing")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.ok(result);
    }

    // View leave history for specific employee
    @GetMapping("/leave-requests/{empId}")
    public ResponseEntity<List<LeaveRequest>> getLeaveHistory(@PathVariable String empId) {
        return ResponseEntity.ok(leaveService.getLeavesForEmployee(empId));
    }
}

