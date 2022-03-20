package br.com.casare86.springData.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.casare86.springData.dto.EmployeeProjection;
import br.com.casare86.springData.model.EmployeeModel;
import br.com.casare86.springData.model.OccupationModel;
import br.com.casare86.springData.model.UnitModel;
import br.com.casare86.springData.repository.EmployeeRepository;
import br.com.casare86.springData.repository.OccupationRepository;
import br.com.casare86.springData.repository.UnitRepository;

@Service
public class EmployeeService {
	
	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final OccupationRepository occupationRepository;
	private final EmployeeRepository employeeRepository;
	private final UnitRepository unitRepository;
	
	
	public EmployeeService(EmployeeRepository employeeRepository, 
			OccupationRepository occupationRepository, UnitRepository unitRepository) {
		this.employeeRepository = employeeRepository;
		this.occupationRepository = occupationRepository;
		this.unitRepository = unitRepository;
	}
	
	public void init(Scanner scanner) {
		while(system) {
			System.out.println("Qual acao de cargo deseja executar");
			System.out.println("0 - Quit");
			System.out.println("1 - Save");
			System.out.println("2 - Update");
			System.out.println("3 - List");
			System.out.println("4 - Delete");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				save(scanner);
				break;
			case 2:
				update(scanner);
				break;
			case 3:
				listAll(scanner);
				break;
			case 4:
				delete(scanner);
				break;
			default:
				system = false;
				break;
			}
		}
	}
	
	private void save(Scanner scanner) {
		System.out.println("Input name");
        String nome = scanner.next();

        System.out.println("Input cpf");
        String cpf = scanner.next();

        System.out.println("Input salario");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data de contracao");
        String dataContratacao = scanner.next();

        System.out.println("Input cargoId");
        Long occupationId = scanner.nextLong();

        List<UnitModel> unidades = units(scanner);

        EmployeeModel funcionario = new EmployeeModel();
        funcionario.setName(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalary(new BigDecimal(salario));
        funcionario.setContractDate(LocalDate.parse(dataContratacao, formatter));
        Optional<OccupationModel> cargo = occupationRepository.findById(occupationId);
        funcionario.setOccupation(cargo.get());
        funcionario.setUnits(unidades);

        employeeRepository.save(funcionario);
        System.out.println("Salvo");
	}
	
	private List<UnitModel> units(Scanner scanner) {
        Boolean isTrue = true;
        List<UnitModel> unidades = new ArrayList<>();

        while (isTrue) {
            System.out.println("Input unitId (0 to quit)");
            Long unitId = scanner.nextLong();

            if(unitId != 0) {
                Optional<UnitModel> unidade = unitRepository.findById(unitId);
                unidades.add(unidade.get());
            } else {
                isTrue = false;
            }
        }

        return unidades;
    }
	
	private void update(Scanner scanner) {
		System.out.println("Input id");
        Long id = scanner.nextLong();

        System.out.println("Input nome");
        String nome = scanner.next();

        System.out.println("Input cpf");
        String cpf = scanner.next();

        System.out.println("Input salario");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data de contracao");
        String dataContratacao = scanner.next();

        System.out.println("Input occupationId");
        Long cargoId = scanner.nextLong();

        EmployeeModel funcionario = new EmployeeModel();
        funcionario.setId(id);
        funcionario.setName(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalary(new BigDecimal(salario));
        funcionario.setContractDate(LocalDate.parse(dataContratacao, formatter));
        Optional<OccupationModel> cargo = occupationRepository.findById(cargoId);
        funcionario.setOccupation(cargo.get());

        employeeRepository.save(funcionario);
        System.out.println("Alterado");
	}
	
	private void listAll(Scanner scanner) {
		System.out.println("What page would you like to visit? ");
		
		int pageNumber = scanner.nextInt();
		Pageable pageable = PageRequest.of(pageNumber,  5, Sort.by(Sort.Direction.ASC, "name"));// Sort.unsorted() -> default by ID
		Page<EmployeeModel> employees = employeeRepository.findAll(pageable);
		
		System.out.println(employees);
		System.out.println("Page number:" + employees.getNumber() + " out of " + employees.getTotalPages());
		System.out.println("Total employees: " + employees.getTotalElements());
		
//		Iterable<EmployeeModel> funcionarios = employeeRepository.findAll();
//		funcionarios.forEach(funcionario -> System.out.println(funcionario));
	}
	
	private void delete(Scanner scanner) {
		System.out.println("input Employee ID:");
		Long id = scanner.nextLong();
		employeeRepository.deleteById(id);
		System.out.println("Deletado");
	}
	
	private void listEmployeeSalaries() {
		List<EmployeeProjection> list = employeeRepository.findEmployeesIdNameAndSalary();
		list.forEach(f -> System.out.println("Employee: " + f.getId() + "name: " + f.getName() +  " sal.: " + f.getSalary() ));
	}
	
}
