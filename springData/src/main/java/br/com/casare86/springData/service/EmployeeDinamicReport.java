package br.com.casare86.springData.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.casare86.springData.model.EmployeeModel;
import br.com.casare86.springData.repository.EmployeeRepository;
import br.com.casare86.springData.specification.EmployeeSpecification;

@Service
public class EmployeeDinamicReport {
	
	private final EmployeeRepository employeeRepository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public EmployeeDinamicReport(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public void init(Scanner scanner) {
		
		System.out.println("Input name:");
		String name = scanner.next();
		if(name.equalsIgnoreCase("NULL"))
			name = null;
		
		System.out.println("Input cpf:");
		String cpf = scanner.next();
		if(cpf.equalsIgnoreCase("NULL"))
			cpf = null;
			
		
		System.out.println("Input salary:");
		BigDecimal salary = new BigDecimal(scanner.nextDouble());
		if(salary.equals(BigDecimal.ZERO))
			salary = null;
			
		LocalDate date;
		System.out.println("Input contract date:");
		String strDate = scanner.next();
		if(strDate.equalsIgnoreCase("NULL"))
			date = null;
		else
			date = LocalDate.parse(strDate, formatter);
		
		
		List<EmployeeModel> list = employeeRepository.findAll(Specification
				.where(
					EmployeeSpecification.name(name))
					.or(EmployeeSpecification.cpf(cpf))
					.or(EmployeeSpecification.salary(salary))
					.or(EmployeeSpecification.contractDate(date))
				);
		list.forEach(System.out::println);
		
		
	}
}
