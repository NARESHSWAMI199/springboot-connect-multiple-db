package com.onlineHrms.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.onlineHrms.model.blog.Blog;



@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
	
	@Query(value="select * from blog" , nativeQuery=true)
	List<Blog> getBlog();

}
