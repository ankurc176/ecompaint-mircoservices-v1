package com.coder.complaintservice.Exception;

import org.springframework.stereotype.Component;

@Component
public class CannotDeactivateException extends Exception{

    @Override
    public String toString() {
        return "Cannot DeActivate Complaint!!. Please Reply to the Complaint First";
    }
}
