package hcmute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcmute.entity.Friendship;
import hcmute.repository.FriendshipRepository;

@Service
public class FriendshipServiceImpl implements IFriendshipService{
	@Autowired
	FriendshipRepository friendshipRepository;

	@Override
	public <S extends Friendship> S save(S entity) {
		return friendshipRepository.save(entity);
	}

	@Override
	public List<Friendship> getFriendshipsByUser2(Long userId) {
		return friendshipRepository.findByUser2(userId);
	}

	@Override
	public List<Friendship> findFriendshipsByUser1OrUser2(Long user1, Long user2) {
		return friendshipRepository.findByUser1OrUser2(user1, user2);
	}

	@Override
	public List<Friendship> findFriendshipsByUser1AndUser2(Long user1, Long user2) {
		return friendshipRepository.findFriendshipsByUser1AndUser2(user1, user2);
	}
	
	@Override
	public List<Friendship> getFriendshipList2(Long user2, String status) {
        return friendshipRepository.findByUser2AndStatus( user2, status);
    }
	
	@Override
	public List<Friendship> getFriendshipList1(Long user1, String status) {
        return friendshipRepository.findByUser1AndStatus( user1, status);
    }

}
