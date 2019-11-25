package springmvc_example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springmvc_example.model.User;
import springmvc_example.service.UserService;

@RestController
public class UserController {

 @Autowired
 UserService userService;
 
 
 /* ---- Fetch Data ----*/
	@RequestMapping(value = "/user", method = RequestMethod.GET, headers ="Accept=application/json")
	public ResponseEntity<List<User>> getListUser(){
		
		List<User> list = userService.getListUser();
		/* System.out.println("Success"); */
		
		if(list.size()==0) 
		{
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
 
 /* ---- Add Data ----*/
 @RequestMapping(value="/add", method=RequestMethod.POST)
 public @ResponseBody ResponseEntity<User> add(@RequestBody User user){
  userService.saveOrUpdate(user);
  
  HttpHeaders header = new HttpHeaders();
  return new ResponseEntity<User>(header, HttpStatus.CREATED);
 }
 
 /* ---- Update Data ----*/
 @RequestMapping(value="/update/{id}", method=RequestMethod.PUT)
 public @ResponseBody User update(@PathVariable("id") int id, @RequestBody User user){
  user.setId(id);
  userService.saveOrUpdate(user);
  
  return user;
 }
 
 /* ---- Delete Data ----*/
 @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
 public @ResponseBody User delete(@PathVariable("id") int id){
  User user = userService.findUserById(id);
  userService.deleteUser(id);
  
  return user;
 }
 
}