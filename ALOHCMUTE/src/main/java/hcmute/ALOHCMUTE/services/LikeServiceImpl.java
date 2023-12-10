package hcmute.ALOHCMUTE.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcmute.ALOHCMUTE.entity.Post;
import hcmute.ALOHCMUTE.entity.User;
import hcmute.ALOHCMUTE.repository.LikeRepository;

@Service
public class LikeServiceImpl implements ILikeService{
	
	@Autowired
	LikeRepository likeRepository;
	
	
	
	@Override
	public List<Post> topLike() {
		return likeRepository.topLike();
	}

	@Override
	public int countLikesByPostId(Post postid) {
		return likeRepository.countLikesByPostId(postid);
	}

	@Override
	public int countLikesByUserId(User userid) {
		return likeRepository.countLikesByUserId(userid);
	}
	
	
}
