package hcmute.ALOHCMUTE.services;

import java.util.List;
import java.util.Optional;

import hcmute.ALOHCMUTE.entity.Post;

public interface IPostService {

	List<Post> findAll();

	<S extends Post> S save(S entity);

	Optional<Post> findById(Long id);

	void deleteById(Long id);

	

}
