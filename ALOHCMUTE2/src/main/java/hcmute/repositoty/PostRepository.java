package hcmute.repositoty;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hcmute.entity.Comments;
import hcmute.entity.Post;
import hcmute.entity.User;
import jakarta.transaction.Transactional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
	@Query("SELECT c FROM Comments c WHERE c.postid = :postid ORDER BY c.cmtDate DESC")
	public List<Comments> getCommentsByPostId(@Param("postid") Post postid);
	
	@Query("SELECT c FROM Comments c WHERE c.cmtid = :cmtid")
	public Optional<Comments> getCommentsByCmtId(@Param("cmtid") Long cmtid);
	
	@Query("SELECT c FROM Post c ORDER BY c.postDate DESC")
	public List<Post> findAllByOrderByPostDateDesc();
	
	@Query("SELECT COUNT(p) FROM Post p WHERE p.userid = :userid")
	public int countPostByUserId(@Param("userid") User userid);

	@Query("SELECT p FROM Post p WHERE p.userid = :userid ORDER BY p.postDate DESC")
	public List<Post> findAllPostByUserId(@Param("userid") User userid);
	
	@Modifying
	@Transactional
	@Query( "DELETE FROM Post  WHERE postid = :postid")
	public void deletePost(@Param("postid") Long postid);
	
}
