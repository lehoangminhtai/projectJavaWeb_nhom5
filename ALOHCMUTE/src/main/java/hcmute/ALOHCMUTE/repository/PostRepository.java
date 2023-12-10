package hcmute.ALOHCMUTE.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hcmute.ALOHCMUTE.entity.Comments;
import hcmute.ALOHCMUTE.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	@Query("SELECT c FROM Comments c WHERE c.postid = :postid ORDER BY c.cmtDate DESC")
	public List<Comments> getCommentsByPostId(@Param("postid") Post postid);
	
	@Query("SELECT c FROM Comments c WHERE c.cmtid = :cmtid")
	public Optional<Comments> getCommentsByCmtId(@Param("cmtid") Long cmtid);
	
	@Query("SELECT c FROM Post c ORDER BY c.postDate DESC")
	public List<Post> findAllByOrderByPostDateDesc();
}
