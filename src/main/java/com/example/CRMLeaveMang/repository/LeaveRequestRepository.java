package com.example.CRMLeaveMang.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CRMLeaveMang.model.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long>{

	List<LeaveRequest> findByEmployeeEmpId(String empId);
    List<LeaveRequest> findByStatus(String status); // For HR
    
    List<LeaveRequest> findBySeenByTLFalse();  // For TL notifications

    List<LeaveRequest> findBySeenByTLTrueAndSeenByHRFalse(); // For HR notifications
}
