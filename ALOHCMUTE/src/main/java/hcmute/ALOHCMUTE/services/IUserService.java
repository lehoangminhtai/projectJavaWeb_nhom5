package hcmute.ALOHCMUTE.services;

import java.util.List;
import java.util.Optional;

import hcmute.ALOHCMUTE.entity.User;

public interface IUserService {

	User getCurrentUser(String username);

	User getCurrentUserById(int userid);

	long count();

	

	

}
