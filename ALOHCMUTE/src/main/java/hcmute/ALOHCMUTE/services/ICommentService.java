package hcmute.ALOHCMUTE.services;

import java.util.Optional;

import hcmute.ALOHCMUTE.entity.Comments;

public interface ICommentService {

	<S extends Comments> S save(S entity);

	Optional<Comments> findById(Long cmtid);

	void deleteById(Long id);

}
