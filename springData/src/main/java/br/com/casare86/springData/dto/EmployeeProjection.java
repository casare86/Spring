package br.com.casare86.springData.dto;

import java.math.BigDecimal;


public interface EmployeeProjection {
	
	//Interface based Projection - its like a DTObut easier to whrite since it has fewer conditions to attend
	
	Long getId();
	String getName();
	BigDecimal getSalary();
}
