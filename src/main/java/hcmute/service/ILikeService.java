package hcmute.service;

import java.util.List;

import hcmute.entity.Likes;
import hcmute.entity.Post;
import hcmute.entity.User;


public interface ILikeService {
	int countLikesByPostId(Post postid);

	int countLikesByUserId(User userid);

	List<Post> topLike();

	void delLike(Long userid, Long postid);

	void deleteLike(User userid, Post postid);

	boolean existsByUserIdAndPostId(User userid, Post postid);

	void insertLike(User userid, Post postid);

	<S extends Likes> S save(S entity);
}
