package hcmute.repositoty;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hcmute.entity.User;
@Repository
public interface  SearchRepository extends JpaRepository<User, Long>{
	List<User> findByFullNameContaining(String fullName);

	List<User> findByUserId(Long userId);
}
