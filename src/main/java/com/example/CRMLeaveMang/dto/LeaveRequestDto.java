package com.example.CRMLeaveMang.dto;

import java.time.LocalDate;

public class LeaveRequestDto {

	
	    private String empId;
	    private String leaveType; // PAID, SICK, CASUAL, HALF_DAY, LATE, EMERGENCY
	    private LocalDate fromDate;
	    private LocalDate toDate;
	    private String reason;
	    private String email; 
	    
	    
	    
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getEmpId() {
			return empId;
		}
		public void setEmpId(String empId) {
			this.empId = empId;
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
	    
	    
}
