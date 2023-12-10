package hcmute.service;

import java.util.List;

import hcmute.entity.Friendship;

public interface IFriendshipService {
	<S extends Friendship> S save(S entity);
	
	List<Friendship> getFriendshipsByUser2(Long userId);
	
	List<Friendship> findFriendshipsByUser1OrUser2(Long user1, Long user2);
	
	List<Friendship> findFriendshipsByUser1AndUser2(Long user1, Long user2);
}