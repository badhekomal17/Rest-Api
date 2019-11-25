package springmvc_example.service;

import java.util.List;

import springmvc_example.model.User;

public interface UserService {
 
 public List getListUser();
 
 public void saveOrUpdate(User user);
 
 public void deleteUser(int id);
 
 public User findUserById(int id);
 
}