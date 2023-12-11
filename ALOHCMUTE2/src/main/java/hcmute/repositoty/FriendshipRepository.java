package hcmute.repositoty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hcmute.entity.Friendship;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long>{

	<S extends Friendship> S save(S entity);
	
	List<Friendship> findByUser2(Long userId);
	
	List<Friendship> findByUser1OrUser2(Long user1, Long user2);
	
	List<Friendship> findFriendshipsByUser1AndUser2(Long user1, Long user2);
	
	List<Friendship> findByUser2AndStatus(Long user2, String status);
	
	List<Friendship> findByUser1AndStatus(Long user1, String status);
	
	Friendship findByUser1AndUser2(Long user1, Long user2);
}
