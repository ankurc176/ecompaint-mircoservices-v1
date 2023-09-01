package com.coder.complaintservice.service;

import com.coder.complaintservice.Exception.CannotDeactivateException;
import com.coder.complaintservice.RequestDTO.ComplaintRequest;
import com.coder.complaintservice.ResponseDTO.ComplaintResponse;
import com.coder.complaintservice.model.Complaint;
import com.coder.complaintservice.model.Status;
import com.coder.complaintservice.repository.ComplaintRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepo complaintRepo;

    public ComplaintResponse createComplaint(ComplaintRequest complaintRequest) {

        Complaint complaint = new Complaint();

        complaint.setRollNo(complaintRequest.getRollNo());
        complaint.setMessage(complaintRequest.getMessage());
        complaint.setDepartment(complaintRequest.getDepartment());
        complaint.setDate(LocalDateTime.now());
        complaint.setStatus(Status.ACTIVE);


        Complaint save = complaintRepo.save(complaint);
        if(save!=null) {
            return new ComplaintResponse(save.getComplaintId(),
                    save.getRollNo(),
                    save.getMessage(),
                    save.getDepartment(),
                    save.getDate(),
                    save.getStatus()
            );
        }
        return null;
    }

    public List<Complaint> getListOfActiveComplaint() {

        List<Complaint> complaintList = complaintRepo.findAll();

        List<Complaint> activeList =  complaintList.stream().filter(
                active -> active.getStatus().equals(Status.ACTIVE))
                .collect(Collectors.toList());

        return activeList;
    }

    public String deactivateComplaint(String departmentName, Integer complaintId) throws CannotDeactivateException {

        Optional<Complaint> complaintList = complaintRepo.findByDepartmentAndComplaintId(departmentName, complaintId);

        if (complaintList.isPresent()) {
            Complaint complaint = complaintList.get();
            try {
                if(complaint.getReply().isBlank())
                {
                    throw new CannotDeactivateException();
                }
                else {
                    complaint.setStatus(Status.INACTIVE);
                    complaintRepo.save(complaint);
                    return "Status InActive";
                }
            } catch (Exception e) {
                return "Cannot Change Status,Reply is Null";
            }
        }
        else {
            return "Complaint Not Found";
        }
    }

}
