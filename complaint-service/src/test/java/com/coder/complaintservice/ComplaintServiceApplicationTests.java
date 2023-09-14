package com.coder.complaintservice;

import com.coder.complaintservice.model.Complaint;
import com.coder.complaintservice.model.Status;
import com.coder.complaintservice.repository.ComplaintRepo;
import com.coder.complaintservice.service.ComplaintService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class ComplaintServiceApplicationTests {

	@Autowired
	public ComplaintRepo complaintRepo;

	@Autowired
	public ComplaintService complaintService;

	@Test
	public void createComplaintControllerTest(){
		Complaint complaint = new Complaint();

		complaint.setComplaintId(1);
		complaint.setRollNo(12);
		complaint.setMessage("Hey");
		complaint.setDepartment("CSC");
		complaint.setDate(LocalDateTime.now());
		complaint.setStatus(Status.ACTIVE);

		Complaint repo = complaintRepo.save(complaint);
		Assertions.assertEquals(1, repo.getComplaintId());
		Assertions.assertEquals(12, repo.getRollNo());
		Assertions.assertEquals("Hey", repo.getMessage());
	}

	@Test
	public void getListOfActiveComplaintTest() {

		List<Complaint> complaintStream = Stream.of(
				new Complaint(1, 12, "Hey", "CSD",
						LocalDateTime.now(), "YES", Status.ACTIVE),
				new Complaint(2, 13, "Hey Message", "ECE",
						LocalDateTime.now(), "NO", Status.ACTIVE)
		).collect(Collectors.toList());


		Assertions.assertEquals(2,complaintStream.size());

	}


}
