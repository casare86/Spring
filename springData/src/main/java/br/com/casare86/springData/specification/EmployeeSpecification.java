package br.com.casare86.springData.specification;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.data.jpa.domain.Specification;

import br.com.casare86.springData.model.EmployeeModel;

public class EmployeeSpecification {
	public static Specification<EmployeeModel> name(String name){
		return (root, criteriaQuery, criteriaBuilder) -> 
		criteriaBuilder.like(root.get("name"), "%" + name + "%");
	}
	//equals
	public static Specification<EmployeeModel> cpf(String cpf){
		return (root, criteriaQuery, criteriaBuilder) -> 
		criteriaBuilder.equal(root.get("cpf"), cpf);
	}
	
	public static Specification<EmployeeModel> salary(BigDecimal salary){
		return (root, criteriaQuery, criteriaBuilder) -> 
		criteriaBuilder.greaterThan(root.get("salary"), salary);
	}
	
	public static Specification<EmployeeModel> contractDate(LocalDate date){
		return (root, criteriaQuery, criteriaBuilder) -> 
		criteriaBuilder.greaterThan(root.get("contractDate"), date);
	}
}
