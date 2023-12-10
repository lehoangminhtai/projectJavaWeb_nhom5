package hcmute.ALOHCMUTE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hcmute.ALOHCMUTE.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("SELECT u FROM User u WHERE u.userName = :user_name")
	public User getUserByUsername(@Param("user_name") String userName);
	
	@Query("SELECT u FROM User u WHERE u.userid = :userid")
	public User getUserByUserID(@Param("userid") int userid);
	
}
