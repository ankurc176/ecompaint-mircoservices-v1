package com.coder.complaintservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "complainttable")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long complaintId;
    private int rollNo;
    private String message;
    private String department;
    private LocalDateTime date;
    private String reply;
    @Enumerated(EnumType.STRING)
    private Status status;


}
