package br.com.casare86.springData.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.casare86.springData.model.OccupationModel;
import br.com.casare86.springData.repository.OccupationRepository;

@Service
public class OccupationService {
	
	private final OccupationRepository occupationRepository;
	private boolean execute;
	
	public OccupationService(OccupationRepository repository) {
		this.occupationRepository = repository;
	}

	/**
	 * @return the occupationRepository
	 */
	public OccupationRepository getOccupationRepository() {
		return occupationRepository;
	}
	
	public void init(Scanner scanner) {
		while(execute) {
			System.out.println("Choose what to do:");
			System.out.println("0 - Quit");
			System.out.println("1 - Save");
			System.out.println("2 - Update");
			
			int action = scanner.nextInt();
			switch (action) {
			case 1:
				save(scanner);
				break;
			case 2:
				update(scanner);
				break;
			case 3:
				getOccupations();
				break;
			case 4:
				delete(scanner);
				break;
			case 0:	
			default:
				execute = false;
				break;
			}
		}
	}
	
	private void save(Scanner scanner) {
		System.out.println("Occupation Description: ");
		String description = scanner.next();
		OccupationModel occupation = new OccupationModel();
		occupation.setDescription(description);
		occupationRepository.save(occupation);
		System.out.println("New Occupation registered succesfully!");
	}
	
	private void update(Scanner scanner) {
		System.out.println("ID: ");
		Long id = scanner.nextLong();
		
		Optional<OccupationModel> occupation = occupationRepository.findById(id);
		if(occupation.isPresent()) {
			OccupationModel model = occupation.get();
			System.out.println("Previous description: " + model.getDescription());
			System.out.println("New Occupation Description: ");
			String description = scanner.next();
			model.setDescription(description);
			//save do an update when the object already have an ID set
			occupationRepository.save(model);
			return;
		}
		System.out.println("There is no registry with id: " + id);
	}
	
	private void getOccupations() {
		Iterable<OccupationModel> occupations = occupationRepository.findAll();
		occupations.forEach(occupation -> System.out.println(occupation));
	}
	
	private void delete(Scanner scanner) {
		System.out.println("ID: ");
		Long id = scanner.nextLong();
		occupationRepository.deleteById(id);
		System.out.println("Occupation deleted.");
	}
	
}
