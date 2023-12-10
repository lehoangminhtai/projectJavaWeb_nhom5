package hcmute.ALOHCMUTE.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcmute.ALOHCMUTE.entity.User;
import hcmute.ALOHCMUTE.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	 private UserRepository userRepository;
	@Override
	public User getCurrentUser(String username) {
		 return userRepository.getUserByUsername(username);
	 }
	
	@Override
	public User getCurrentUserById(int userid) {
		 return userRepository.getUserByUserID(userid);	 
	 }
	 
}
