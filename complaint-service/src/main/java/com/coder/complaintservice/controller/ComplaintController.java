package com.coder.complaintservice.controller;

import com.coder.complaintservice.Exception.CannotDeactivateException;
import com.coder.complaintservice.RequestDTO.ComplaintRequest;
import com.coder.complaintservice.ResponseDTO.ComplaintResponse;
import com.coder.complaintservice.model.Complaint;
import com.coder.complaintservice.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @PostMapping()
    public ResponseEntity<ComplaintResponse> createComplaintController(@RequestBody ComplaintRequest complaintRequest) {

        return new ResponseEntity<>(complaintService.createComplaint(complaintRequest),HttpStatus.CREATED);

    }

    @GetMapping()
    public ResponseEntity<List<Complaint>> getListOfActiveComplaints() {

        return new ResponseEntity<>(complaintService.getListOfActiveComplaint(), HttpStatus.OK);
    }

    @PutMapping("/{departmentName}/{id}")
    public ResponseEntity<String> deactivateComplaint(@PathVariable String departmentName,@PathVariable Integer id) {

        String result = null;

        try {
            result = complaintService.deactivateComplaint(departmentName, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public void getAllComplaint() {

    }

}
