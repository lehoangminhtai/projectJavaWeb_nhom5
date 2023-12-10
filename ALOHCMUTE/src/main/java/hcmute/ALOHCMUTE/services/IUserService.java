package hcmute.ALOHCMUTE.services;


import hcmute.ALOHCMUTE.entity.User;

public interface IUserService {

	User getCurrentUser(String username);

	User getCurrentUserById(int userid);

	

	

}
