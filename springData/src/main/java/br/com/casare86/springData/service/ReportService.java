package br.com.casare86.springData.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.stereotype.Service;

import br.com.casare86.springData.model.EmployeeModel;
import br.com.casare86.springData.repository.EmployeeRepository;

@Service
public class ReportService {
	
	private boolean execute;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private EmployeeRepository employeeRepository;
	
	public ReportService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public void init(Scanner scanner) {
		while(execute) {
			System.out.println("Choose what to do:");
			System.out.println("0 - Quit");
			System.out.println("1 -Search by Name (Employee)");
			System.out.println("2 - Update");
			
			int action = scanner.nextInt();
			switch (action) {
			case 1:
				System.out.println("Searching by name...");
				searchByName(scanner);
				break;
			case 2:
				searchByNameContractDateAndSalaryGreater(scanner);
				break;
			case 3:
				findByContractDateNativeQuery(scanner);
				break;
			default:
				execute = false;
				break;
			}
		}
	}
	
	public void searchByName(Scanner scanner) {
		System.out.println("Input employee´s name to search:");
		String name = scanner.next();
		List<EmployeeModel> list = employeeRepository.findByName(name);
		list.forEach(System.out::println);
	}
	
	private void searchByNameContractDateAndSalaryGreater(Scanner scanner){
		System.out.println("Input employee name:");
		String name = scanner.next();
		System.out.println("Input contract date: (dd/mm/yyyy)");
		String date = scanner.next();
		LocalDate localDate = LocalDate.parse(date, formatter); 
		
		System.out.println("Input salary(greater then):");
		Double salary = scanner.nextDouble();
		BigDecimal salaryBigD = new BigDecimal(salary);
		
		List<EmployeeModel> list = employeeRepository.findByNameContractDateAndSalaryGreater(name, salaryBigD, localDate);
		list.forEach(System.out::println);
	}
	
	private void findByContractDateNativeQuery(Scanner scanner){
		System.out.println("Search for contracts after date: (dd/mm/yyyy)");
		String date = scanner.next();
		LocalDate localDate = LocalDate.parse(date, formatter); 
		List<EmployeeModel> list = employeeRepository.findByContractDateNativeQuery(localDate);
		list.forEach(System.out::println);
	}
}