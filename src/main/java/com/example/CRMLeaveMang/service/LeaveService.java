//package com.example.CRMLeaveMang.service;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import com.example.CRMLeaveMang.dto.LeaveRequestDto;
//import com.example.CRMLeaveMang.model.Employee;
//import com.example.CRMLeaveMang.model.LeaveRequest;
//import com.example.CRMLeaveMang.repository.LeaveRequestRepository;
//import com.example.CRMLeaveMang.repository.EmployeeRepository;
//
//@Service
//public class LeaveService {
//
//    @Autowired
//    private LeaveRequestRepository leaveRequestRepository;
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public String applyLeave(LeaveRequestDto dto) {
//        Optional<Employee> empOpt = employeeRepository.findByEmpId(dto.getEmpId());
//
//        if (!empOpt.isPresent()) {
//            return "Employee Not Found";
//        }
//
//        Employee emp = empOpt.get();
//
//        if (dto.getFromDate().isBefore(LocalDate.now().plusDays(2))) {
//            return "Leave must be applied at least 2 days in advance";
//        }
//
//        long days = dto.getToDate().toEpochDay() - dto.getFromDate().toEpochDay() + 1;
//        double leaveDays = (dto.getLeaveType().equalsIgnoreCase("HALF_DAY")) ? 0.5 : days;
//
//        emp.setTakenLeaves(emp.getTakenLeaves() + leaveDays);
//        employeeRepository.save(emp);
//
//        LeaveRequest leave = new LeaveRequest();
//        leave.setEmployee(emp);
//        leave.setEmpId(emp.getEmpId());
//        leave.setEmail(emp.getEmail());
//        leave.setLeaveType(dto.getLeaveType());
//        leave.setFromDate(dto.getFromDate());
//        leave.setToDate(dto.getToDate());
//        leave.setReason(dto.getReason());
//        leave.setStatus("PENDING");
//        leave.setAppliedOn(LocalDate.now());
//        leave.setEmail(emp.getEmail());
//
//        leaveRequestRepository.save(leave);
//
//        return "Leave Request Submitted";
//    }
//
//    public List<LeaveRequest> getLeavesForEmployee(String empId) {
//        return leaveRequestRepository.findByEmployeeEmpId(empId);
//    }
//
//    public List<LeaveRequest> getAllLeaveRequests() {
//        return leaveRequestRepository.findAll();
//    }
//
//    public String updateLeaveStatus(Long leaveId, String status) {
//        Optional<LeaveRequest> leaveOpt = leaveRequestRepository.findById(leaveId);
//
//        if (!leaveOpt.isPresent()) {
//            return "Leave request not found";
//        }
//
//        LeaveRequest leave = leaveOpt.get();
//        leave.setStatus(status.toUpperCase());
//        leaveRequestRepository.save(leave);
//
//        String email = leave.getEmail();
//        if (email == null || email.isEmpty()) {
//            return "Employee email is missing";
//        }
//
//        String subject = "Leave Request " + status.toUpperCase();
//        String message = "Hi,\n\nYour leave request from " + leave.getFromDate()
//                         + " to " + leave.getToDate() + " for reason: " + leave.getReason()
//                         + " has been " + status.toUpperCase() + ".\n\nThanks,\nHR Department";
//
//        sendEmail(email, subject, message);
//
//        return "Leave status updated and email sent to employee.";
//    }
//
//    public void sendEmail(String toEmail, String subject, String body) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(toEmail);
//        message.setSubject(subject);
//        message.setText(body);
//        mailSender.send(message);
//    }
//}


package com.example.CRMLeaveMang.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.CRMLeaveMang.dto.LeaveRequestDto;
import com.example.CRMLeaveMang.model.Employee;
import com.example.CRMLeaveMang.model.LeaveRequest;
import com.example.CRMLeaveMang.repository.LeaveRequestRepository;
import com.example.CRMLeaveMang.repository.EmployeeRepository;

@Service
public class LeaveService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JavaMailSender mailSender;

    public String applyLeave(LeaveRequestDto dto) {
        Optional<Employee> empOpt = employeeRepository.findByEmpId(dto.getEmpId());

        if (!empOpt.isPresent()) {
            return "Employee Not Found";
        }

        Employee emp = empOpt.get();

        if (dto.getFromDate().isBefore(LocalDate.now().plusDays(2))) {
            return "Leave must be applied at least 2 days in advance";
        }

        long days = dto.getToDate().toEpochDay() - dto.getFromDate().toEpochDay() + 1;
        double leaveDays = (dto.getLeaveType().equalsIgnoreCase("HALF_DAY")) ? 0.5 : days;

        emp.setTakenLeaves(emp.getTakenLeaves() + leaveDays);
        employeeRepository.save(emp);

        LeaveRequest leave = new LeaveRequest();
        leave.setEmployee(emp);
        leave.setEmpId(emp.getEmpId());
        leave.setLeaveType(dto.getLeaveType());
        leave.setFromDate(dto.getFromDate());
        leave.setToDate(dto.getToDate());
        leave.setReason(dto.getReason());
        leave.setStatus("PENDING");
        leave.setAppliedOn(LocalDate.now());

        String emailToUse = dto.getEmail();
        if (emailToUse == null || emailToUse.isEmpty()) {
            emailToUse = emp.getEmail();
        }
        leave.setEmail(emailToUse);

        leaveRequestRepository.save(leave);

        return "Leave Request Submitted";
    }

    public List<LeaveRequest> getLeavesForEmployee(String empId) {
        return leaveRequestRepository.findByEmployeeEmpId(empId);
    }

    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }
    

//    public String updateLeaveStatus(Long leaveId, String status) {
//        Optional<LeaveRequest> leaveOpt = leaveRequestRepository.findById(leaveId);
//
//        if (!leaveOpt.isPresent()) {
//            return "Leave request not found";
//        }
//
//        LeaveRequest leave = leaveOpt.get();
//        leave.setStatus(status.toUpperCase());
//        leaveRequestRepository.save(leave);
//
//        String email = leave.getEmail();
//        if (email == null || email.isEmpty()) {
//            return "Employee email is missing";
//        }
//
//        String subject = "Leave Request " + status.toUpperCase();
//        String message = "Hi,\n\nYour leave request from " + leave.getFromDate()
//                + " to " + leave.getToDate() + " for reason: " + leave.getReason()
//                + " has been " + status.toUpperCase() + ".\n\nThanks,\nHR Department";
//
//        sendEmail(email, subject, message);
//
//        return "Leave status updated and email sent to employee.";
//    }
    
    public String updateLeaveStatus(Long leaveId, String status) {
        Optional<LeaveRequest> leaveOpt = leaveRequestRepository.findById(leaveId);

        if (!leaveOpt.isPresent()) {
            return "Leave request not found";
        }

        LeaveRequest leave = leaveOpt.get();
        leave.setStatus(status.toUpperCase());
        leaveRequestRepository.save(leave);

        // Update employee leave stats only if status is APPROVED
        if (status.equalsIgnoreCase("APPROVED")) {
            Employee emp = leave.getEmployee();
            long days = leave.getToDate().toEpochDay() - leave.getFromDate().toEpochDay() + 1;
            double leaveDays = leave.getLeaveType().equalsIgnoreCase("HALF_DAY") ? 0.5 : days;

            emp.setTakenLeaves(emp.getTakenLeaves() + leaveDays);
            emp.setBalanceleave((int) (emp.getTotalLeaves() - emp.getTakenLeaves()));
            employeeRepository.save(emp);
        }

        String email = leave.getEmail();
        if (email == null || email.isEmpty()) {
            return "Employee email is missing";
        }

        String subject = "Leave Request " + status.toUpperCase();
        String message = "Hi,\n\nYour leave request from " + leave.getFromDate()
                + " to " + leave.getToDate() + " for reason: " + leave.getReason()
                + " has been " + status.toUpperCase() + ".\n\nThanks,\nHR Department";

        sendEmail(email, subject, message);

        return "Leave status updated and email sent to employee.";
    }


    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}

