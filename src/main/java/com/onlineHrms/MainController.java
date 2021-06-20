package com.onlineHrms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlineHrms.model.blog.Blog;
import com.onlineHrms.model.person.Person;



@RequestMapping(value="/")

@RestController
public class MainController {

	
	
	@Autowired
	MainService mainService;
	
	@GetMapping("/blog")
	public List<Blog> showBlog() {
		return mainService.getBlogData();
	}
	

	@GetMapping("/person")
	public List<Person> ShowPerson() {
		return mainService.getPersonData();
	}
	
}
