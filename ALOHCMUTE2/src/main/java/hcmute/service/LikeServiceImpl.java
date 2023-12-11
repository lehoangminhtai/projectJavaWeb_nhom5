package hcmute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcmute.entity.Likes;
import hcmute.entity.Post;
import hcmute.entity.User;
import hcmute.repositoty.LikeRepository;


@Service
public class LikeServiceImpl implements ILikeService{

	@Autowired
	LikeRepository likeRepository;

	public LikeServiceImpl(LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}
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
	
	@Override
	public <S extends Likes> S save(S entity) {
		return likeRepository.save(entity);
	}

	@Override	
	public void insertLike(User userid, Post postid) {
		Likes like = new Likes();
        like.setUserid(userid);
        like.setPostid(postid);

        likeRepository.save(like);
	}
	
	@Override
	public boolean existsByUserIdAndPostId(User userid,Post postid) {
		return likeRepository.existsByUserIdAndPostId(userid, postid);
	}

	@Override
	public void deleteLike(User userid, Post postid) {
		Long user = userid.getUserId();
		Long post = postid.getPostid();
		delLike(user, post);
	}
	
	@Override
	public void delLike(Long userid, Long postid) {
		likeRepository.delLike(userid, postid);
	}
	
}
