package hcmute.ALOHCMUTE.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hcmute.ALOHCMUTE.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
