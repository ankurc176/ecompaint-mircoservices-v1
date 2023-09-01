package com.coder.complaintservice.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintRequest {

    private int rollNo;
    private String message;
    private String department;

}
