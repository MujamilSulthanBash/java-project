package com.ems.exception;

public class EmployeeException extends Exception {
  
    public EmployeeException(String errorMessage, Throwable err) {  
        super(errorMessage, err);  
    }  

} 