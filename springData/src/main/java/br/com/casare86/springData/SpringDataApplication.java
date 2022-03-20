package br.com.casare86.springData;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.casare86.springData.service.EmployeeDinamicReport;
import br.com.casare86.springData.service.EmployeeService;
import br.com.casare86.springData.service.OccupationService;
import br.com.casare86.springData.service.ReportService;
import br.com.casare86.springData.service.UnitService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {
	
	private boolean system = true;
	
	private final OccupationService occupationService;
	private final EmployeeService employeeService;
	private final UnitService unitService;
	private final ReportService reportService;
	private final EmployeeDinamicReport employeeDinamicReport;
	
	public SpringDataApplication(OccupationService occupationService, EmployeeService employeeService,
			UnitService unitService, ReportService reportService, EmployeeDinamicReport employeeDinamicReport) {
		this.occupationService = occupationService;
		this.employeeService = employeeService;
		this.unitService = unitService;
		this.reportService = reportService;
		this.employeeDinamicReport = employeeDinamicReport;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		while(system) {
			System.out.println("Choose one option:");
			System.out.println("0 - Quit");
			System.out.println("1 - Occupation");
			System.out.println("2 - Employee");
			System.out.println("3 - Unit");
			System.out.println("4 - Report");
			System.out.println("5 - Specifications");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				occupationService.init(scanner);
				break;
			case 2:
				employeeService.init(scanner);
				break;
			case 3:
				unitService.init(scanner);
				break;
			case 4:
				reportService.init(scanner);
				break;
			case 5:
				employeeDinamicReport.init(scanner);
				break;

			default:
				system = false;
				break;
			}
		}
	}
}
