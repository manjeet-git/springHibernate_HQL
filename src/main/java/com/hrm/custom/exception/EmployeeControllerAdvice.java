package com.hrm.custom.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EmployeeControllerAdvice extends ResponseEntityExceptionHandler{
        final static String EMPLOYEE_ID_BAD_REQUEST_MESSAGE="This employee id doesn't exist and Information doesn't found";
	
        
        @ExceptionHandler(EmployeeNotFoundException.class)
	public final ResponseEntity<ErrorResponse> employeeNoDetailsNotFoundException(EmployeeNotFoundException employeeException, WebRequest webrequest){
		List<String> errors=new ArrayList();
		errors.add(employeeException.getLocalizedMessage());
		
		ErrorResponse response=new ErrorResponse();
		response.setMsg(EMPLOYEE_ID_BAD_REQUEST_MESSAGE);
		response.setErrors(errors);
		
		return new ResponseEntity<ErrorResponse>(response,HttpStatus.BAD_REQUEST);
		
		
	}
}
