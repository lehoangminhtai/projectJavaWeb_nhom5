package hcmute.service;
import hcmute.entity.Category;
import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
public interface ICategoryService {
	void deleteAll();

	void delete(Category entity);

	void deleteById(Long id);

	long count();

	Optional<Category> findById(Long id);

	List<Category> findAllById(Iterable<Long> ids);

	List<Category> findAll(Sort sort);

	Page<Category> findAll(Pageable pageable);

	List<Category> findAll();

	<S extends Category> S save(S entity);

	List<Category> findByCategoryNameContaining(String name);

	Page<Category> findByCategoryNameContaining(String name, Pageable pageable);
}
