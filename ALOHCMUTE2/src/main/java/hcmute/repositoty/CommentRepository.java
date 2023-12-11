package hcmute.repositoty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hcmute.entity.Comments;
import hcmute.entity.Post;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long>{
	@Query("SELECT COUNT(c) FROM Comments c WHERE c.postid = :postid")
	public int countCommentsByPostId(@Param("postid") Post postid);
	
	@Query("SELECT c.postid, COUNT(c) AS cmtCount FROM Comments c GROUP BY c.postid ORDER BY cmtCount DESC")
	public List<Post> topCmt();
}
