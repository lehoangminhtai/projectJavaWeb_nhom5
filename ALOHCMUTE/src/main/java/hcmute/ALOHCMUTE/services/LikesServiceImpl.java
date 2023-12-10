package hcmute.ALOHCMUTE.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hcmute.ALOHCMUTE.entity.Likes;
import hcmute.ALOHCMUTE.entity.Post;
import hcmute.ALOHCMUTE.entity.User;
import hcmute.ALOHCMUTE.repository.LikesRepository;

@Service
public class LikesServiceImpl implements ILikesService{

	@Autowired
	private LikesRepository likeRep;
	
	
	@Override
	public int countLikesByPostId(Post postid) {
		return likeRep.countLikesByPostId(postid);
	}

	@Override
	public <S extends Likes> S save(S entity) {
		return likeRep.save(entity);
	}
	
	@Override
	public Likes findByUserIdAndPostId(int userid, Long postid) {
		return likeRep.findByUserIdAndPostId(userid, postid);
	}
	
	@Override	
	public void insertLike(User userid, Post postid) {
		Likes like = new Likes();
        like.setUserid(userid);
        like.setPostid(postid);

        likeRep.save(like);
	}
	
	@Override
	public boolean existsByUserIdAndPostId(User userid,Post postid) {
		return likeRep.existsByUserIdAndPostId(userid, postid);
	}

	@Override
	public void deleteLike(User userid, Post postid) {
		int user = userid.getUserid();
		Long post = postid.getPostid();
		delLike(user, post);
	}
	
	@Override
	public void delLike(int userid, Long postid) {
		likeRep.delLike(userid, postid);
	}
}
