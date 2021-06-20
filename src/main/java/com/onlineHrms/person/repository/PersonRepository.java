package com.onlineHrms.person.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.onlineHrms.model.person.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

	
	@Query(value="select * from person" , nativeQuery=true)
	List<Person> getPerson();
	
}
