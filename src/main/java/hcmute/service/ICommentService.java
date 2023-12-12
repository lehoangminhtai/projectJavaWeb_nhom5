package hcmute.service;

import java.util.List;
import java.util.Optional;

import hcmute.entity.Comments;
import hcmute.entity.Post;


public interface ICommentService {
	<S extends Comments> S save(S entity);

	Optional<Comments> findById(Long cmtid);

	void deleteById(Long id);

	int countCommentsByPostId(Post postid);

	long count();

	List<Post> topCmt();
}
