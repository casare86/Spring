package br.com.casare86.springData.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="employees")
public class EmployeeModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String cpf;
	private BigDecimal salary;
	private LocalDate contractDate;
	
	@ManyToOne
	@JoinColumn(name = "occupation_id", nullable = false)
	private OccupationModel occupation;
	
	@Fetch(FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "employees_units", joinColumns = {
			@JoinColumn(name = "fk_employee") }, 
	inverseJoinColumns = { @JoinColumn(name = "fk_unit") })
	private List<UnitModel> units;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public LocalDate getContractDate() {
		return contractDate;
	}
	public void setContractDate(LocalDate contractDate) {
		this.contractDate = contractDate;
	}
	public OccupationModel getOccupation() {
		return occupation;
	}
	public void setOccupation(OccupationModel occupation) {
		this.occupation = occupation;
	}
	public List<UnitModel> getUnits() {
		return units;
	}
	public void setUnits(List<UnitModel> units) {
		this.units = units;
	}
	@Override
	public String toString() {
		return "EmployeeModel [id=" + id + ", name=" + name + ", cpf=" + cpf + ", salary=" + salary + ", contractDate="
				+ contractDate + ", occupation=" + occupation + ", units=" + units + "]";
	}
	
}
