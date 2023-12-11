package hcmute.repositoty;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hcmute.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.username = :user_name")
	public User getUserByUsername(@Param("user_name") String userName);
	
	@Query("SELECT u FROM User u WHERE u.userId = :userid")
	public User getUserByUserID(@Param("userid") Long userid);
}
