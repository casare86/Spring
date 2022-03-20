package br.com.casare86.springData.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.casare86.springData.model.UnitModel;

@Repository
public interface UnitRepository extends CrudRepository <UnitModel, Long>{
	

}
