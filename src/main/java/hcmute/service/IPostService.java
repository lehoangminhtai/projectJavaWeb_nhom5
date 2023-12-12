package hcmute.service;

import java.util.List;
import java.util.Optional;

import hcmute.entity.Comments;
import hcmute.entity.Post;
import hcmute.entity.User;


public interface IPostService {
	List<Post> findAll();

	<S extends Post> S save(S entity);

	Optional<Post> findById(Long id);

	void deleteById(Long id);

	List<Comments> getCommentsByPostId(Post postid);

	Optional<Comments> getCommentsByCmtId(Long cmtid);

	List<Post> findAllByOrderByPostDateDesc();

	long countPost();

	int countPostByUserId(User userid);

	long countCmt();

	List<Post> findAllPostByUserId(User userid);

	void deletePost(Long postid);
}
