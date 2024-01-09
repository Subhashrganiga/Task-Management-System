package com.jsp.TaskManagement.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.jsp.TaskManagement.DTO.Task;
import com.jsp.TaskManagement.REPOSITORY.TaskRepository;


@Repository				
public class Taskdao 			//DAO class id used to perform all the database operation
{
	@Autowired
	TaskRepository repository; 			// has-a relationship
	
		public String addTask(Task task)		//return type may be anything this is an example for string return type
			{
				repository.save(task);
				return " Task is added successfully";
			}
//		public Task addTask(Task task)			//return type is class object 
//		{
//			return repository.save(task);
//		}

		
		//to search for task objectt based on id
		public Task findTaskbyID(int id)
		{
			Optional<Task> opt =repository.findById(id);		//optional class introduced in java 8th version and we cant store even null value also
																		//to deal with nullpointerexception we'll use if id present object will be stored else empty and null will not be stored
			
			if(opt.isPresent())
			{
				return opt.get();
			}
			return null;
		}
		
		// to fetch all task objects from Database
		public List<Task> getAllTask()
		{
			return repository.findAll();			//it will return in list
		}
		
		//to delete task from database
		public String deleteById(int id) 
		{
			Task t= findTaskbyID(id);				//first we have to check object is present or not
			if(t!=null)
			{
				repository.deleteById(id);
				return "Task deleted";
			}
			return null;
		}
		
		//to update task from database
		public String updateById(int id, String newstatus) 
		{
			Task t= findTaskbyID(id);
			if(t!=null)
			{
				t.setStatus(newstatus);
				repository.save(t);						//to save in database
				return "Task updated......!";
			}
			return null;
		}
}
