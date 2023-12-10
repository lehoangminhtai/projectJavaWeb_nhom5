package hcmute.ALOHCMUTE.services;

import java.util.List;
import java.util.Optional;

import hcmute.ALOHCMUTE.entity.Comments;
import hcmute.ALOHCMUTE.entity.Post;

public interface ICommentService {

	<S extends Comments> S save(S entity);

	Optional<Comments> findById(int id);

	int countCommentsByPostId(Post postid);

	long count();

	List<Post> topCmt();

}
