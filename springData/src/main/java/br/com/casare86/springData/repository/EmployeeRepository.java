package br.com.casare86.springData.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.casare86.springData.dto.EmployeeProjection;
import br.com.casare86.springData.model.EmployeeModel;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeModel, Long> {
	
	List<EmployeeModel> findByName(String name);
	
	//Derived Queries - spring generates the SQL if you specifies the fields/columns you want
	List<EmployeeModel> findByNameOrderByNameAsc(String name); //ordering by name ASC
	List<EmployeeModel> findByNameIsNotNull(); //name is not null or findByNameIsNull() for null
	List<EmployeeModel> findByNameEndingWith(String name); //or findByNomeStartingWith to position %name or name%
	List<EmployeeModel> findByNameLike(String name); //using like SQL search
	
	@Query("SELECT e FROM EmployeeModel e WHERE e.name = :name AND e.salary >= :salary AND e.contractDate = :date")
	List<EmployeeModel> findByNameContractDateAndSalaryGreater(String name, BigDecimal salary, LocalDate date);

	//Derived Queries also work with relations (JOIN)
	//same as: @Query("SELECT e FROM EmployeeModel e JOIN e.occupation o WHERE o.description = :description")
	List<EmployeeModel> findByOccupationDescription(String description); //make the join and search by description
	//Example for tables with composite name
	//@Query("SELECT f FROM Funcionario f JOIN f.unidadeTrabalhos u WHERE u.descricao = :descricao")
	//List<Funcionario> findByUnidadeTrabalhos_Descricao(String descricao);
	
	List<EmployeeModel> findByContractDateGreaterThanEqual(LocalDate date);
	
	@Query(value = "SELECT * FROM employees e WHERE e.contract_date >= :date", nativeQuery = true)
	List<EmployeeModel> findByContractDateNativeQuery(LocalDate date);
	
	@Query(value = "SELECT e.id, e.name, e.salary FROM employees e", nativeQuery = true)
	List<EmployeeProjection> findEmployeesIdNameAndSalary();

	List<EmployeeModel> findAll(Specification<EmployeeModel> where);
}
