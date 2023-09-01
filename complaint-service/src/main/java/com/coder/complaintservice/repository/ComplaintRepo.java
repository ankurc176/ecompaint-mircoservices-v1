package com.coder.complaintservice.repository;

import com.coder.complaintservice.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComplaintRepo extends JpaRepository<Complaint,Integer> {

    Optional<Complaint> findByDepartmentAndComplaintId(String department, int complaintId);

}
