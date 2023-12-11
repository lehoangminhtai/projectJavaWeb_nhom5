package hcmute.service;



import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import hcmute.entity.User;

public interface IUserService {
	boolean authenticate(String username, String password);
    User findByUsername(String username);
    Optional<User> findById(Long id);
    <S extends User> S save(S entity);
	void deleteAll();
	void delete(User entity);
	void deleteById(Long id);
	long count();
	<S extends User> Optional<S> findOne(Example<S> example);
	List<User> findAllById(Iterable<Long> ids);
	List<User> findAll(Sort sort);
	Page<User> findAll(Pageable pageable);
	List<User> findAll();
	User getCurrentUser(String username);

	User getCurrentUserById(Long userid);
	
	User registerNewUser(User user);
}
