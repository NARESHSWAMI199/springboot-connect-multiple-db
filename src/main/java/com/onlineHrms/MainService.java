package com.onlineHrms;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineHrms.blog.repository.BlogRepository;
import com.onlineHrms.model.blog.Blog;
import com.onlineHrms.model.person.Person;
import com.onlineHrms.person.repository.PersonRepository;

@Service
public class MainService {
	
	@Autowired
	PersonRepository personRepository;
	@Autowired
	BlogRepository blogRepository;
	

	public List<Person> getPersonData(){
		return personRepository.getPerson();
	}

	public List<Blog> getBlogData(){
		return blogRepository.getBlog();
	}

}
