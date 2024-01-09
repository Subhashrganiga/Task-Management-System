package com.jsp.TaskManagement.CONTROLLER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.TaskManagement.DAO.Taskdao;
import com.jsp.TaskManagement.DTO.Task;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/task")
public class TaskController
{												//3 things client should specify url,type of request  and one task object i.e createtask in this program
	@Autowired
	Taskdao dao;
											//each method is one api
	@PostMapping		//endpont - url  each method handle request url and acessing through url
	public ResponseEntity<String> createTask(@Valid @RequestBody Task task,BindingResult bindingResult)		//the incoming object is json object(client send json to understand by java we need to convert) by using @requestbody specifies and converts into java object
	{
		//return dao.addTask(task);				//dao will perform next operation so send it to dao by using has a relationship
		 if (bindingResult.hasErrors()) {
	            // Handle validation errors
	            return new ResponseEntity<>("Invalid input data: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
	        }else {
	        	 dao.addTask(task);
	        	return new ResponseEntity<>("Task created successfully", HttpStatus.CREATED);
	        }
	          
	}	
	
	@GetMapping("/{id}")
	public Task findById(@PathVariable int id)			//@RequestParam used to give singlr value 
	{
		return dao.findTaskbyID(id);
	}
	
	@GetMapping("/taskAll")				//if mapping is same then url shold bbe different 
	public List findAllTask()
	{
		return dao.getAllTask();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTaskById(@Valid  @RequestParam int id, BindingResult bindingResult)
	{
		//return dao.deleteById(id);
		if (bindingResult.hasErrors()) {
            // Handle validation errors
            return new ResponseEntity<>("Invalid input data: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }else {
        	dao.deleteById(id); 
        	return new ResponseEntity<>("Task deleted successfully", HttpStatus.CREATED);
        }
		
	}
	
	@PutMapping
	public ResponseEntity<String>  updateTaskById(@Valid @RequestParam int id,@RequestParam String status, BindingResult bindingResult)
	{
		//return dao.updateById(id, status);					
		if (bindingResult.hasErrors()) {
            // Handle validation errors
            return new ResponseEntity<>("Invalid input data: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }else {
        	dao.updateById(id, status);	
        	return new ResponseEntity<>("Task updated successfully", HttpStatus.CREATED);
        }
	}
}
