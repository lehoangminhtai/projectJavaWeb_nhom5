package hcmute.ALOHCMUTE.services;

import hcmute.ALOHCMUTE.entity.Likes;
import hcmute.ALOHCMUTE.entity.Post;
import hcmute.ALOHCMUTE.entity.User;

public interface ILikesService {

	int countLikesByPostId(Post postid);

	<S extends Likes> S save(S entity);

	void insertLike(User userid, Post postid);

	Likes findByUserIdAndPostId(int userid, Long postid);

	void delLike(int userid, Long postid);

	void deleteLike(User userid, Post postid);

	boolean existsByUserIdAndPostId(User userid, Post postid);
}
