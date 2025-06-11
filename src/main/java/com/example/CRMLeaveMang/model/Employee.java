package com.example.CRMLeaveMang.model;

import jakarta.persistence.*;

@Entity

public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empId;         // this for e.g., EMP001
    private String name;
    private String email;
    
	private String department;
    
    private int balanceleave=31;
    private int totalLeaves = 31;
    private double takenLeaves = 0;
    private int halfDayCount = 0;
    private int lateComings = 0;
    
    public int getBalanceleave() {
		return balanceleave;
	}

	public void setBalanceleave(int balanceleave) {
		this.balanceleave = balanceleave;
	}

    
    public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmpId() {
		return empId;
	}




	public void setEmpId(String empId) {
		this.empId = empId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}




	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}




	public int getTotalLeaves() {
		return totalLeaves;
	}

	public void setTotalLeaves(int totalLeaves) {
		this.totalLeaves = totalLeaves;
	}


	public double getTakenLeaves() {
		return takenLeaves;
	}

	public void setTakenLeaves(double takenLeaves) {
		this.takenLeaves = takenLeaves;
	}

	public int getHalfDayCount() {
		return halfDayCount;
	}

	public void setHalfDayCount(int halfDayCount) {
		this.halfDayCount = halfDayCount;
	}


	public int getLateComings() {
		return lateComings;
	}


	public void setLateComings(int lateComings) {
		this.lateComings = lateComings;
	}


	@Transient
    public double getPendingLeaves() {
        return totalLeaves - takenLeaves;
    }
}
