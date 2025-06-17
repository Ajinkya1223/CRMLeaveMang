package com.example.CRMLeaveMang.model;



import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "leave_requests")
public class LeaveRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
    private String empId; 
    private String email; 
    
   
	private String leaveType;
    private LocalDate fromDate;
    private LocalDate toDate;
    private String reason;
    private String status = "PENDING";
    private LocalDate appliedOn;
    
    @Column(name = "SeenByTL")
    private boolean seenByTL = false;

    @Column(name = "SeenByHR")
    private boolean seenByHR = false;
    
    
    public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmail() {
		return email;
	}
    
	public Long getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public LocalDate getFromDate() {
		return fromDate;
	}
	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}
	public LocalDate getToDate() {
		return toDate;
	}
	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getAppliedOn() {
		return appliedOn;
	}
	public void setAppliedOn(LocalDate appliedOn) {
		this.appliedOn = appliedOn;
	}
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		 this.email = email;
	}
	public boolean isSeenByTL() {
		return seenByTL;
	}
	public void setSeenByTL(boolean seenByTL) {
		this.seenByTL = seenByTL;
	}
	public boolean isSeenByHR() {
		return seenByHR;
	}
	public void setSeenByHR(boolean seenByHR) {
		this.seenByHR = seenByHR;
	}
	
	
	
	
    
    

}
