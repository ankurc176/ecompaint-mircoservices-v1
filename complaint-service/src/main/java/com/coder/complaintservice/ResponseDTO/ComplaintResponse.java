package com.coder.complaintservice.ResponseDTO;

import com.coder.complaintservice.model.Status;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintResponse {

    private long complaintId;
    private int rollNo;
    private String message;
    private String department;
    private LocalDateTime date;
    private Status status;


}
