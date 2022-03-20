package br.com.casare86.springData.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.casare86.springData.model.UnitModel;
import br.com.casare86.springData.repository.UnitRepository;

@Service
public class UnitService {
	
	private Boolean system = true;
	private final UnitRepository unitRepository;
	
	public UnitService(UnitRepository unitRepository) {
		this.unitRepository = unitRepository;
	}
	
	public void init(Scanner scanner) {
		while(system) {
			System.out.println("Qual acao de cargo deseja executar");
			System.out.println("0 - Quit");
			System.out.println("1 - Save");
			System.out.println("2 - Update");
			System.out.println("3 - List Units");
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
				list();
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
		System.out.println("Input unit name:");
        String nome = scanner.next();

        System.out.println("Input address:");
        String endereco = scanner.next();

        UnitModel unit = new UnitModel();
        unit.setDescription(nome);
        unit.setAddress(endereco);

        unitRepository.save(unit);
        System.out.println("Unit Saved!");
	}
	
	private void update(Scanner scanner) {
		System.out.println("Input id");
        Long id = scanner.nextLong();

        System.out.println("Unit name:");
        String nome = scanner.next();

        System.out.println("Unit Address");
        String endereco = scanner.next();

        UnitModel unit = new UnitModel();
        unit.setId(id);
        unit.setDescription(nome);
        unit.setAddress(endereco);

        unitRepository.save(unit);
        System.out.println("Updated!");
	}
	
	private void list() {
		Iterable<UnitModel> units = unitRepository.findAll();
		units.forEach(unit -> System.out.println(unit));
	}
	
	private void delete(Scanner scanner) {
		System.out.println("Id");
		Long id = scanner.nextLong();
		unitRepository.deleteById(id);
		System.out.println("Deletado");
	}
}
