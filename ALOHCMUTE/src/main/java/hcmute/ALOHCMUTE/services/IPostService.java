package hcmute.ALOHCMUTE.services;

import java.util.List;
import java.util.Optional;

import hcmute.ALOHCMUTE.entity.Comments;
import hcmute.ALOHCMUTE.entity.Post;
import hcmute.ALOHCMUTE.entity.User;

public interface IPostService {

	List<Post> findAll();

	<S extends Post> S save(S entity);

	Optional<Post> findById(Long id);

	void deleteById(Long id);

	List<Comments> getCommentsByPostId(Post postid);

	Optional<Comments> getCommentsByCmtId(Long cmtid);

	long countCmt();

	int countPostByUserId(User userid);

	List<Post> findAllPostByUserId(User userid);

	long countPost();

	

//	List<Comments> getCommentsByPostId(Long postid);

	

}
