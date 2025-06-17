package com.example.CRMLeaveMang.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CRMLeaveMang.model.LeaveRequest;
import com.example.CRMLeaveMang.repository.LeaveRequestRepository;

@RestController
public class TeamLeaderController {
	
	 @Autowired
	private LeaveRequestRepository leaveRequestRepository;
	 
	// View all unseen leave requests
	    @GetMapping("/notifications")
	 public ResponseEntity<List<LeaveRequest>> getUnseenLeaves() {
	        List<LeaveRequest> unseen = leaveRequestRepository.findBySeenByTLTrueAndSeenByHRFalse();
	        return ResponseEntity.ok(unseen);
	    }
	    
	 // Confirm (mark as seen)
	    @PostMapping("/mark-seen/{leaveId}")
	    public ResponseEntity<String> markAsSeenByTL(@PathVariable Long leaveId) {
	        Optional<LeaveRequest> leaveOpt = leaveRequestRepository.findById(leaveId);
	        if (leaveOpt.isEmpty()) {
	            return ResponseEntity.status(404).body("Leave Request not found");
	        }
	        
	        LeaveRequest leave = leaveOpt.get();
	        leave.setSeenByTL(true);
	        leaveRequestRepository.save(leave);

	        return ResponseEntity.ok("Leave request confirmed by Team Leader.");
	    }
	    
	    


	
}
